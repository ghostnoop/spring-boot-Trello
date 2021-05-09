<#import 'layouts/base.ftl' as base>
<@base.main title="main" board_name=board.title>
    <a class="btn btn-primary" href="/board/${board.id}/column/create">New Column</a>
    <a class="btn btn-primary" href="/board/${board.id}/archived/cards">Archived cards</a>
    <div class="row">
        <#list board.columns as column>
            <div class="col-2 card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${column.title}</h5>
                </div>
            </div>
            <#list column.cards as card>
                <#if !card.isDeleted>
                    <a class="btn btn-primary" href="/card/${card.id}">${card.title}</a>
                </#if>
            </#list>
            <a class="btn btn-primary" href="/column/${column.id}/card/create">New Card</a>
        </#list>
    </div>
</@base.main>