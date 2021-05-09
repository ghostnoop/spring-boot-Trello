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
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                        </div>
                                    </#if>
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">SignUp</h3>
                                    <@spring.bind "signUpForm"/>
                                    <form action="/signUp" autocomplete="off" method="post" id="form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Email</span>
                                            <@spring.formInput "signUpForm.email" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">firstname</span>
                                            <@spring.formInput "signUpForm.firstname" "class='form-control' pattern='^[A-Za-zА-ЯЁа-яё]+$' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Password</span>
                                            <@spring.formPasswordInput "signUpForm.password" "class='form-control'
                                            required "/>
                                            <@spring.showErrors "<br>" "error"/>
                                            <#if passwordsErrorMessage??>
                                                <p class="error">${passwordsErrorMessage}</p>
                                            </#if>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Password Again</span>
                                            <@spring.formPasswordInput "signUpForm.passwordAgain" "class='form-control'
                                            required"/>
                                            <#if passwordsErrorMessage??>
                                                <p class="error">${passwordsErrorMessage}</p>
                                            </#if>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light" id="submit">SignUp
                                        </button>
                                    </form>
                                    <hr>
                                    <p><a href="/signIn" class="text-secondary">SignIn ?</a></p>
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