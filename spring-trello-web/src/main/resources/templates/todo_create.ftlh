<#import "layouts/base.ftl" as base>
<#import "spring.ftl" as spring/>
<@base.main title="SignUp" css=["flatpickr.min.css"] scripts=['flatpickr.js', 'flatpickr_ru.js', 'main.js']>
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <#if error??>
                                        <div style="padding-top: 20px">
                                            <div class="alert alert-danger fade show" role="alert">
                                                ${error}
                                                <button type="button" class="close" data-dismiss="alert"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                        </div>
                                    </#if>
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">New ToDo on ${card.title}</h3>
                                    <form action="/todo/${card.id}/create" autocomplete="off" method="post" id="form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Description</span>
                                            <input type="text" class='form-control' required name="description"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Date</span>
                                            <input class='form-control date-input' required name="expiredDate" type="text"/>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                                id="submit">Create
                                        </button>
                                    </form>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col"></div>
                </div>
            </section>
        </div>
    </main>
</@base.main>