<#import 'layouts/base.ftl' as base>
<@base.main title="main">
    <div class="row">
        <#list boards as board>
            <div class="col-2 card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${board.title}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Users</h6>
                    <ul>
                        <#list board.users as u>
                            <li>${u.email}</li>
                        </#list>
                    </ul>
                    <a href="/board/${board.id}" class="card-link">To board</a>
                </div>
            </div>
        </#list>
    </div>
</@base.main>