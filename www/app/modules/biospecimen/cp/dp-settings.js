
angular.module('os.biospecimen.cp.dp', [])
  .controller('CpDpSettingsCtrl', function($scope, cp, DistributionProtocol, Alerts, AuthorizationService) {
    var defDps = undefined;
    function init() {
      $scope.dpCtx = {
        allowedDps: [],
        editAllowed: AuthorizationService.isAllowed($scope.cpResource.updateOpts)
      };

      initCpDps();
      loadDps();
    }

    function initCpDps() {
      var allowedDps = angular.copy(cp.distributionProtocols);
      angular.forEach(allowedDps, addDisplayValue);
      $scope.dpCtx.allowedDps = allowedDps;
      return allowedDps;
    }

    function loadDps(searchString) {
      if (defDps && !searchString) {
        $scope.dpCtx.dps = defDps;
        return;
      }

      DistributionProtocol.query({query: searchString, cp: cp.shortTitle}).then(
        function(dps) {
          angular.forEach(dps, addDisplayValue);
          $scope.dpCtx.dps = dps;

          if (!searchString) {
            defDps = dps;
          }
        }
      );
    }

    function addDisplayValue(obj) {
      return angular.extend(obj, {itemKey: obj.shortTitle, displayValue: obj.shortTitle});
    }

    $scope.loadDps = loadDps;

    $scope.listChanged = function(action, dp) {
      delete cp.repositoryNames;
      delete cp.extensionDetail;
      delete cp.catalogSetting;

      switch(action) {
        case 'add':
          if (!addDp(dp)) {
            return;
          }

          break;
        case 'remove':
          removeDp(dp);
          break;
        case 'update':
          removeDp({itemKey: dp.shortTitle});
          if (!addDp({itemKey: dp.displayValue})) {
            return;
          }
      }

      return cp.$saveOrUpdate().then(
        function(savedCp) {
          cp.distributionProtocols = savedCp.distributionProtocols;
          return initCpDps();
        }
      );
    }

    function addDp(dp) {
      var error = false;
      angular.forEach(cp.distributionProtocols, function(cpDp) {
        if (cpDp.shortTitle == dp.itemKey) {
          Alerts.error('cp.dp.dup_dp');
          error = true;
        }
      });

      if (error) {
        return;
      }

      cp.distributionProtocols.push({shortTitle: dp.itemKey});
      return cp.distributionProtocols;
    }

    function removeDp(dp) {
      var filteredDps = cp.distributionProtocols.filter(function(cpDp) {
        return cpDp.shortTitle != dp.itemKey;
      });

      cp.distributionProtocols = filteredDps;
    }

    init();
  });
