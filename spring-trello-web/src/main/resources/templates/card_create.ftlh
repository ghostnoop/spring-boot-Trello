<#import "layouts/base.ftl" as base>
<#import "spring.ftl" as spring/>
<@base.main title="SignUp">
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
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">New card on column ${column.title}</h3>
                                    <form action="/column/card/create" autocomplete="off" method="post" id="form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Title</span>
                                            <input type="text" class='form-control' required name="title"/>
                                        </div>
                                        <input type="text" value="${column.id}" name="columnId" hidden>
                                        <div class="form-group">
                                            <span class="text-black-50">Description</span>
                                            <textarea class='form-control' required name="description" rows="3"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Users</span>
                                            <select multiple name="users">
                                                <#list column.board.users as u>
                                                    <#if user.id != u.id>
                                                        <option value="${u.id}">${u.email}</option>
                                                    </#if>
                                                </#list>
                                            </select>
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