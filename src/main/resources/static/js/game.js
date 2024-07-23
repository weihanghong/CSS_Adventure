let game = document.getElementById("content");
let frames = 0;

document.addEventListener("DOMContentLoaded", () => {
    setInterval(updateGame, 1000);
})

const updateGame = () => {
    frames += 1;
    let enemies = document.getElementsByClassName("enemy");
    let player = document.getElementById("player");
    for (let i = 0; i < enemies.length; i++) {
        if(checkCollision(player, enemies[i])) {
            return;
        }
        let r = parseInt(enemies[i].style.right, 10);
        if(isNaN(r)) r = 0;
        enemies[i].style.right = `${r+30}px`;
    }
    if(frames===1 || frames%100===0) {
        let enemy = document.createElement("div");
        enemy.classList.add("enemy");
        game.appendChild(enemy);
    }
}

const checkCollision = (a, b) => {
    let o1 = a.getBoundingClientRect();
    let o2 = b.getBoundingClientRect();
    console.log(o1, o2);
    return !(o1.right < o2.left || o1.left > o2.right || o1.top > o2.bottom || o1.bottom < o2.top);
}