/**
 * Created by tewe on 11/22/2016.
 */
angular.module('services',[])
    //user service
    .factory('UserServices', function($resource) {
    return $resource('/user/:id'); // Note the full endpoint address
})
.factory('PatientServices', function() {
    var patient = {}
    function set(data) {
        patient = data;
    }
    function get() {
        return patient;
    }

    return {
        set: set,
        get: get
    }

});
;