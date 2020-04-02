$(document).ready(function($){
    $("a.delete").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'DELETE',
            url: $(this).attr('href'),
            success: function () {
                window.location = "http://localhost:1025/products";
            },
            error: function () {
                console.log("error")
            }
        });
    });
    //Add product
    $('button.saveProduct[type=submit]').click(function(e) {
        e.preventDefault();

        $('input').next().remove();

        $.ajax({
            type: 'POST',
            url: 'product/addProduct',
            data: $('form[name=product]').serialize(),
            success: function(res) {
                if(res.stat === "SUCCESS") {
                    const id = res.createResponse.product.productID,
                        name = res.createResponse.product.productName;

                    $('input').val("");

                    alert("Product with name: " + name + " has successfuly added with " + id + " ID.");

                } else {
                    $.each(res.validationErrors, function (key, value) {
                        $('.errors').append('<span>'+value+'</span>');
                    });

                    $.each(res.dbErrors, function (key, value) {
                        $('.errors').append('<span>'+value+'</span>');
                    });
                }
            }
        })
    });

    //Edit product
    $('a[type=submit]').click(function(e) {
        e.preventDefault();

        $('input').next().remove();

        $.ajax({
            type: 'PUT',
            url: 'editProduct',
            data: $('form[name=ed_product]').serialize(),
            success: function(res) {
                if(res.validated) {
                    alert("Product has successfuly updated!");
                } else {
                    $.each(res.errorMessages,function(key,value){
                        $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                        $('select[name='+key+']').after('<span class="error">'+value+'</span>');
                    });
                }
            }
        })
    });
    //Add cart
    $('button.saveCart[type=submit]').click(function(e) {
        e.preventDefault();

        $('input').next().remove();

        $.ajax({
            type: 'POST',
            url: 'cart/addCart',
            data: $('form[name=cart]').serialize(),
            success: function(res) {
                if(res.stat === "SUCCESS") {
                    const id = res.cartResponse.cart.cartId,
                        name = res.cartResponse.cart.cartName;

                    $('input').val("");

                    alert("Cart with name: " + name + " has successfuly created with " + id + " ID.");

                } else {
                    $.each(res.validationErrors, function (key, value) {
                        $('.errors').append('<span>'+value+'</span>');
                    });

                    $.each(res.dbErrors, function (key, value) {
                        $('.errors').append('<span>'+value+'</span>');
                    });
                }
            }
        })
    });
});