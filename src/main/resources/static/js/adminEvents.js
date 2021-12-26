function commonAdminLogic(){
    $("#admin-header").load("/admin-parts/admin-header.html")

    function loadMainContent(htmlPageName) {
        $("#admin-main-content").load(`/${htmlPageName}`)
    }
}