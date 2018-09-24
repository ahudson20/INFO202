/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.saleList) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}


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

module.factory('saveDAO', function ($resource) {
    return $resource('/api/sales/');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.controller('CartController', function (cart, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();

    this.buyButton = function(product){
        $sessionStorage.selectedProduct = product;
        $window.location.href = 'purchase.html';
    };
});

module.controller('BuyController', function ($sessionStorage, $window ,cart, saveDAO) {
    this.selectedProduct = $sessionStorage.selectedProduct;

    this.addToCartButton = function(quantity){
        let product = $sessionStorage;
        let p = new SaleItem(product, quantity);
        cart.addItem(p);
        $sessionStorage.cart = cart;
        $window.location.href = "cart.html";
    };

    this.cartButton = function(){
        let customer = $sessionStorage.customer;
        cart.setCustomer(customer);
        saveDAOsave.save();
        $sessionStorage.$reset();
        $window.location.href = "complete.html";
    };
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
    };

    // let selectedProduct;
    // this.onSelect = function (product) {
    //     alert("hello");
    //     selectedProduct = product;
    //     $window.location.href = 'purchase.html';
    // }
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

    this.checkSignIn = function () {
        if($sessionStorage.customer)  {
            // customer exists in session storage
            this.signedIn = true;
        }else{
            this.signedIn = false;
        }
    };

    this.signOut = function () {
        $sessionStorage.$reset();
        this.signedIn = false;
        $window.location.href = 'index.html';
    }
});