
angular.module('os.administrative.models.orderui', ['os.common.models'])
  .factory('DistributionOrderUi', function(osModel) {
    var DistributionOrderUi = new osModel('ui-orders');

    DistributionOrderUi.newOrder = function(order) {
      return new DistributionOrderUi(order);
    };

    return DistributionOrderUi;
  });
