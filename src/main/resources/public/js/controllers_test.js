/**
 * Created by tewe on 11/18/2016.
 */
'use strict'; /* implies undeclared variable should not be used or throw errors otherwise*/

describe('app module', function() {

    beforeEach(module('app'));

    describe('app controllers', function(){

        it('should ....', inject(function($controller) {
            //spec body
            var appCtrl = $controller('homeCtl');
            expect(appCtrl).toBeDefined();
        }));

    });
});
