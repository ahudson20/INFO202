/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";
// create a new module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {
return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
return $resource('/api/categories/:category');
});

module.factory('registerDAO', function ($resource) {
return $resource('/api/register/');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username'); 
});

module.controller('ProductController', function (productDAO, categoryDAO) {
    this.products = productDAO.query();
    // load the categories
    this.categories = categoryDAO.query();

    //this.allProducts = productDaoAll.query();
    
    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"category": selectedCat});
    };

    this.selectAll = function (){
        this.products = productDAO.query();
    }
});

module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {
    //alert("in controller");
    this.signInMessage = "Please sign in to continue.";
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer);
    };
    
    // alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function (username, password) {
            // get customer from web service
            signInDAO.get({'username': username},
            // success
            function (customer) {
            // also store the retrieved customer
            $sessionStorage.customer = customer;
            // redirect to home
            $window.location.href = 'index.html';
            },
            // fail
            function () {
                ctrl.signInMessage = 'Sign in failed. Please try again.';
            }
        );
    };
});