<header class="header-section">
    <nav class="navbar navbar-light bg-light justify-content-between">
        <a class="navbar-brand" href="/boards">Spring Trello</a>
        <span>${board_name}</span>
        <div class="left">
            <div class="weather"></div>
            <a class="btn btn-primary" href="/board/create">New Board</a>
            <#if user??>
                <a class="btn btn-primary" href="/boards">All boards</a>
                <a class="btn btn-primary" href="/logout">Logout</a>
            </#if>
        </div>
    </nav>
</header>