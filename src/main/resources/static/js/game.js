let game = document.getElementById("content");
let frames = 0;
let score = document.getElementById("score");
let interval;
let jump = 0;

document.addEventListener("DOMContentLoaded", () => {
    interval = setInterval(updateGame, 250);
})

const updateGame = () => {
    frames += 1;
    score.innerText = "Score: " + frames;
    let enemies = document.getElementsByClassName("enemy");
    let player = document.getElementById("player");
    for (let i = 0; i < enemies.length; i++) {
        if(checkCollision(player, enemies[i])) {
            clearInterval(interval);
            let result = document.getElementById("result");
            result.style.display = "block";
            let text = document.getElementById("result-text");
            text.value = frames;
            text.innerText = "New Score: " + frames;
            return;
        }
        let r = parseInt(enemies[i].style.right, 10);
        if(isNaN(r)) r = -60;
        enemies[i].style.right = `${r+60}px`;
    }
    if(frames===1 || frames%8===0) {
        let enemy = document.createElement("div");
        enemy.classList.add("enemy");
        const min = 10;
        const max = 260
        enemy.style.bottom = `${Math.random()*(max-min)+min}px`;
        let sprite = document.createElement("div");
        sprite.classList.add("enemy-sprite");
        enemy.appendChild(sprite);
        game.appendChild(enemy);
    }
    let j = parseInt(player.style.bottom, 10);
    if(isNaN(j)) j = 10;
    if(j>10 || jump>0) {
        player.style.bottom = `${j+jump<10?10:j+jump}px`;
        jump -= 40;
    }
}

const checkCollision = (a, b) => {
    let o1 = a.getBoundingClientRect();
    let o2 = b.getBoundingClientRect();
    return !(o1.right < o2.left || o1.left > o2.right || o1.top > o2.bottom || o1.bottom < o2.top);
}

document.addEventListener("keypress", (e) => {
    if(e.code==="Space") {
        jump = 100;
    }
})