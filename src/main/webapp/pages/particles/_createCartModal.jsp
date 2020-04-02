<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Create Cart Modal -->
<div class="modal fade" id="createCartModal" tabindex="-1" role="dialog" aria-labelledby="createCartModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createCartModalLabel">Create Cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="cart/addCart" name="cart">
                    <div class="form-row">
                        <div class="col-12 mb-3">
                            <label for="validationCart">Cart name</label>
                            <input type="text" class="form-control" id="validationCart" name="cartName" />
                            <span class="invalid-feedback">
                                Field can't be empty!
                            </span>
                        </div>
                    </div>
                    <div class="form-row">
                        <button href="cart/addCart" class="btn btn-primary saveCart" type="submit">Save cart</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="errors"></div>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>