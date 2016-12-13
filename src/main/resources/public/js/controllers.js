
var controllers = angular.module('controllers',[]);

controllers.controller('usersCtl', function($scope,$http) {
    $http.get('/users/').success(function(data)
    {
        $scope.greeting = data
    })
    //$scope.greeting = {id: 'xxx', content: 'Hello World!'}
});
controllers.controller('adduserCtl', function($scope,$http) {
    $http.get('/createUser',{'email':$scope.email,'name':$scope.name,'password':$scope.password}).success(function(data)
    {
        $scope.greeting = data
    })
    //$scope.greeting = {id: 'xxx', content: 'Hello World!'}
});

controllers.controller('homeCtl', function($scope, $http) {

    });
controllers.controller('navigationCtl',
        function($rootScope, $scope, $http, $location)
        {
            console.log("We are inside navigation controller after login() is clicked");

            var authenticate = function(credentials, callback) {

                var headers = credentials ? {authorization : "Basic "
                + btoa(credentials.username + ":" + credentials.password)
                } : {};
                 //send the credentials to the backend server
                $http.get('/user', {headers : headers})
                    .success(function(data)
                    {
                        if (data.name) {
                            $rootScope.authenticated = true;
                        } else {
                            $rootScope.authenticated = false;
                            $scope.error=true;
                        }
                        callback && callback();
                    })
                    .error(function() {
                        $rootScope.authenticated = false;
                        $scope.error = true;
                        callback && callback();
                    });

            }
            authenticate();
            $scope.credentials = {};
            $scope.login = function()
            {
                authenticate($scope.credentials, function()
                {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        $scope.error = false;
                    }
                    else
                    {
                        $rootScope.authenticated = false;
                        $location.path("/login");
                        $scope.error = true;
                    }

                })

            };
            $scope.logout = function() {
                $http.post('logout',{})
                    .success(function()
                    {
                    $rootScope.authenticated = false;
                    $location.path("/");
                    })
                    .error(function(data) {
                    $rootScope.authenticated = false;
                });
            }

        });

controllers.controller('radiographCtl', function($scope,$http)
    {
        // fetch doctor radiographs

        $scope.getRadiographs = function () {
            $http.get('/radiographs').success(function(data)
            {
                $scope.radiograph = data;
            })
            .error(function (data, status) {
                $scope.error=true;
            })

        }
        $scope.addradiograph = function () {
            /*
            * As the default header Content-Type set by Angular is json so we need to change to "x-www-form-urlencoded"
            * that is being done using config object in which we have defined headers.
            * */
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            };
            var data = $.param({
                pin: $scope.pin,
                title: $scope.title,
                description: $scope.description
            });

            $http.post("/addradiograph",data,config)
                .success(function (data, status, headers, config) {
                  //$scope.redirectTo= '/';
                })
                .error(function (data, status, header, config) {
                    $scope.error=true;

                });
            
        };

    });
controllers.controller('patientCtl', function($rootScope, $scope, $http, $location)
{
    $scope.patients={};
    // fetch doctor radiographs
    $http.get('/patients').success(function(data)
    {
        $scope.patients = data;
    });

    /*Post function parameters are: url,data,config*/
    $scope.getPatientRadiographs = function () {
        var inputData = {'patient_Id':$scope.patient_Id};
        $http.get('/radiographs', inputData).success(function(data)
        {
            $scope.radiograph = data;
        })
            .error(function (data) {
                $scope.error=true;
            });
    }
    $scope.addPatientRadiograph = function () {
        // pipe selected patient's data to addradiograph controller
       // PatientServices.set($scope.patient);
        //$root.patient= $scope.patient
        redirectTo:'/addradiograph';
    };

    $scope.addpatient = function ()
    {
        /*
         * As the default header Content-Type set by Angular is json so we need to change to "x-www-form-urlencoded"
         * that is being done using config object in which we have defined headers.
         * */
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;',
                'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept;',
                'Access-Control-Allow-Methods': 'GET, POST, PUT'
            }
        };
        console.info("just before sending addpatient request to backend");
        var data = $.param({
            pin: $scope.pin,
            firstname: $scope.firstname,
            lastname: $scope.lastname,
            gender: $scope.gender
        });

        $http.post("/addpatient",data,config)
            .success(function (data, status, headers, config) {
                $scope.patient =data;
                //$scope.redirectTo= '/patients';
            })
            .error(function (data, status, header, config) {
                $scope.error=true;
            });


    };


});

