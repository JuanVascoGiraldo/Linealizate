
const isVisible = "is-visible";

function crm() {
    document.getElementById('modal31').classList.remove(isVisible);
}

function crm2() {
    document.getElementById('modal3').classList.remove(isVisible);
}

function mod() {
    document.getElementById('modalMod').classList.remove(isVisible);
}

function reg() {
    document.getElementById('modalR1').classList.remove(isVisible);
}

function definirmod(){
    const openEls = document.querySelectorAll("[data-open]");
    
    for (const el of openEls) {
    el.addEventListener("click", function() {
        const modalId = this.dataset.open;
        document.getElementById(modalId).classList.add(isVisible);
    });
    }

    document.addEventListener("click", e => {
        if (e.target == document.querySelector(".modal.is-visible")) {
            document.querySelector(".modal.is-visible").classList.remove(isVisible);
        }
    });
}

definirmod();