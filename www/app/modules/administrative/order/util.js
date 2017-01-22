
angular.module('os.administrative.order')
  .factory('OrderUtil', function($http, $translate, Alerts, ApiUrls, DistributionOrder) {

     var SPMN_ERROR_CODES = [
       'DIST_ORDER_INVALID_SPECIMENS_FOR_DP',
       'DIST_ORDER_NON_CONSENTING_SPECIMENS',
       'DIST_ORDER_SPECIMEN_DOES_NOT_EXIST',
       'DIST_ORDER_NOT_ALLOWED'
     ]
    
     function saveOrUpdate(order) {
       var url = ApiUrls.getBaseUrl() + 'ui-orders/';
       var promise;
       if (!order.id) {
         promise = $http.post(url, order.$saveProps());
       } else {
         url += order.id;
         promise = $http.put(url, order.$saveProps());
       }

       return promise.then(
         function(resp) {
           var payload = resp.data;
           if (!payload.errorType) {
             return new DistributionOrder(payload);
           }

           var spmnErrors = {}, otherErrors = []; 
           angular.forEach(payload.errors,
             function(error) {
               if (SPMN_ERROR_CODES.indexOf(error.code) > -1) {
                 addSpmnError(spmnErrors, error);
               } else {
                 otherErrors.push(error);
               }
             }
           );

           angular.forEach(otherErrors, displayApiError);
           return spmnErrors;
         }
       );
     }

     function addSpmnError(spmnErrors, error) {
       var uiMsg = $translate.instant('orders.api_errors.' + error.code);
       angular.forEach(error.params[0],
         function(label) {
           if (spmnErrors[label]) {
             spmnErrors[label] += '\n' + uiMsg;
           } else {
             spmnErrors[label] = uiMsg;
           }
         }
       );
     }

     function displayApiError(error) {
       Alerts.error(error.message + ' (' + error.code + ')');
     }

     return {
       saveOrUpdate: saveOrUpdate
     }
  }
);
