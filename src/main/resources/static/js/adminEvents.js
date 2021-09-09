function commonAdminLogic(){
    $("#admin-header").load("/admin-parts/admin-header.html")
    // $("#admin-side-menu").load("/admin-parts/admin-side-menu.html")
    // $("#admin-main-content").load("/admin-parts/admin-main-content.html")
    // $(`#${activeMenu}`).addClass("active")
    // $(document).on('click', '.admin-side-menu-element', function (){
    //     $('.admin-side-menu-element').removeClass('active');
    //     $(this).addClass("active");
    // });

    function loadMainContent(htmlPageName) {
        $("#admin-main-content").load(`/${htmlPageName}`)
    }
}