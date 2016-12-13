angular.module('app', ['ngRoute', 'controllers'])

    .config(function($routeProvider, $httpProvider){
        $routeProvider
            .when('/',{
                templateUrl: 'home.html',
                controller: 'homeCtl',
                title:"Radiograph information system with Spring boot and AngularJS"
            })
            .when('/login',{
                templateUrl: 'login.html',
                controller: 'navigationCtl',
                title:"Please Login"
            })

            .when('/patients',{
                templateUrl: 'patients.html',
                controller: 'patientCtl',
                title:"List of patients"
            })
            .when('/addpatient',{
                templateUrl: 'addpatient.html',
                controller: 'patientCtl',
                title:"Add patient"
            })

            .when('/users',{
                templateUrl: 'index.html',
                controller: 'usersCtl',
                title:"Radiograph infosys users"
            })
            .when('/adduser',{
                templateUrl: 'index.html',
                controller: 'adduserCtl',
                title:"Add User"
            })
            .when('/radiographs',
                {
                    templateUrl: 'radiographs.html',
                    controller: 'radiographCtl',
                    title:"List of Radiographs per doctor"
                })
            .when('/addradiograph',
                {
                    templateUrl: 'addradiograph.html',
                    controller: 'radiographCtl',
                    title:"Add Radiograph"

    })

            .when('/403',
                {
                templateUrl: 'index.html',
                controller: 'choiceCtl',
                title:"Access denied",
                redirectTo:"403.html"
                })

            .otherwise(
                { redirectTo: '/'}
            );
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    })
// change Page Title based on the routers
   .run(['$location', '$rootScope', function($location, $rootScope)
   {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous)
    {
        if (current.hasOwnProperty('$$route')) {

            $rootScope.title = current.$$route.title;
        }

    });
    }]);



