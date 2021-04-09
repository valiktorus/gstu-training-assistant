(function initializeEvents() {
    muscleGroupSearchEvent();
    equipmentSearchEvent();
    sortingEvents();
    paginationEvents();
})();

function muscleGroupSearchEvent() {
    const muscleSearchFiltration = document.getElementsByClassName("muscle-group-filtration-link");
        Array.from(muscleSearchFiltration).forEach((element => element.onclick = function (event) {
        const searchUrl = new URL("/exercises", document.location);
        searchUrl.searchParams.append("muscleGroup", event.target.innerText);
        addEquipmentToUrl(searchUrl);
        addSortedByToUrl(searchUrl);
        window.location.href = decodeURI(searchUrl.toString());
    }));
}

function equipmentSearchEvent() {
    const equipmentFiltration = document.getElementsByClassName("equipment-filtration-link");
    Array.from(equipmentFiltration).forEach((element => element.onclick = function (event) {
        const searchUrl = new URL("/exercises", document.location);
        searchUrl.searchParams.append("equipment", event.target.innerText);
        addMuscleGroupToUrl(searchUrl);
        addSortedByToUrl(searchUrl);
        window.location.href = decodeURI(searchUrl.toString());
    }));
}

function sortingEvents() {
    const sortedLinks = document.getElementsByClassName("sorting-link");
    Array.from(sortedLinks).forEach((element => element.onclick = function (event) {
        const searchUrl = new URL("/exercises", document.location);
        searchUrl.searchParams.append("sortedBy", event.target.innerText);
        addEquipmentToUrl(searchUrl);
        addMuscleGroupToUrl(searchUrl);
        window.location.href = decodeURI(searchUrl.toString());
    }));
}

function paginationEvents() {
    const pageLinks = document.getElementsByClassName("page-link");
    Array.from(pageLinks).forEach((element => element.onclick = function (event) {
        const pageLink = event.target;
        const pageNumber = pageLink.innerHTML;
        const paginationUrl = new URL("/exercises", document.location);
        paginationUrl.searchParams.append("page", pageNumber);
        addSortedByToUrl(paginationUrl);
        addMuscleGroupToUrl(paginationUrl);
        addEquipmentToUrl(paginationUrl);
        window.location.href = decodeURI(paginationUrl.toString());
    }));
}

function addEquipmentToUrl(url) {
    const activeEquipment = document.getElementById("selected-equipment");
    url.searchParams.append("equipment", activeEquipment.innerText);
}

function addSortedByToUrl(url) {
    const sortedBy = document.getElementById("selected-sorting-field");
    url.searchParams.append("sortedBy", sortedBy.innerText);
}

function addMuscleGroupToUrl(url) {
    const muscleGroup = document.getElementById("selected-muscle-group");
    url.searchParams.append("muscleGroup", muscleGroup.innerText);

}