<#import 'layouts/base.ftl' as base>
<@base.main title="main">
    <div class="row">
        <#list cards as card>
            <#if card.isDeleted>
                <a class="btn btn-primary" href="/card/${card.id}/unarchived">${card.title} - unarchived</a>
            </#if>
        </#list>
    </div>
</@base.main>