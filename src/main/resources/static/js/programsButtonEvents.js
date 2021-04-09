(function initializeProgramEvents() {
    programsDifficultySearchEvent();
    programsSortingEvents();
    programPaginationEvents();
})();

function programsDifficultySearchEvent() {
    const difficultyFiltration = document.getElementsByClassName("difficulty-filtration-link");
        Array.from(difficultyFiltration).forEach((element => element.onclick = function (event) {
        const searchUrl = new URL("/programs", document.location);
        searchUrl.searchParams.append("difficulty", event.target.innerText);
        addProgramSortedByToUrl(searchUrl);
        window.location.href = decodeURI(searchUrl.toString());
    }));
}
function programsSortingEvents() {
    const sortedLinks = document.getElementsByClassName("sorting-link");
    Array.from(sortedLinks).forEach((element => element.onclick = function (event) {
        const searchUrl = new URL("/programs", document.location);
        searchUrl.searchParams.append("sortedBy", event.target.innerText);
        addProgramDifficultyToUrl(searchUrl);
        window.location.href = decodeURI(searchUrl.toString());
    }));
}
function programPaginationEvents() {
    const pageLinks = document.getElementsByClassName("page-link");
    Array.from(pageLinks).forEach((element => element.onclick = function (event) {
        const pageLink = event.target;
        const pageNumber = pageLink.innerHTML;
        const paginationUrl = new URL("/programs", document.location);
        paginationUrl.searchParams.append("page", pageNumber);
        addProgramDifficultyToUrl(paginationUrl);
        addProgramSortedByToUrl(paginationUrl);
        window.location.href = decodeURI(paginationUrl.toString());
    }));
}

function addProgramDifficultyToUrl(url) {
    const activeEquipment = document.getElementById("selected-difficulty");
    url.searchParams.append("difficulty", activeEquipment.innerText);
}

function addProgramSortedByToUrl(url) {
    const sortedBy = document.getElementById("selected-sorting-field");
    url.searchParams.append("sortedBy", sortedBy.innerText);
}