const getMap = ()=>{
        fetch('/map')
        .then(response => response.json())
        .then(data => {
            let gridOverlay = document.querySelector('#grid-overlay');
            gridOverlay.style.backgroundSize = `calc(100% / ${data.width}) calc(100% / ${data.height})`;
            getObstacles(data);
        })
        .catch(error => {
            console.error('Error fetching the map size:', error);
        });
}

const setElementOnMap = (element, img)=>{
    let gridOverlay = document.querySelector('#grid-overlay');
    let gridSize = 16;
    let cellWidth = gridOverlay.offsetWidth / gridSize;
    let cellHeight = gridOverlay.offsetHeight / gridSize;
    let elementIMG = document.createElement('img');
    elementIMG.src = img;
    elementIMG.classList.add('elementOnMap');
    if (img =='/img/roverMars.png'){
        elementIMG.classList.add('img-rover');
    }
    let posX = element.x;
    let posY = element.y;
    elementIMG.style.position = 'absolute !important';
    elementIMG.style.left = `${posX * cellWidth}px`;
    elementIMG.style.top = `${(gridSize - posY - 1) * cellHeight}px`;
    gridOverlay.appendChild(elementIMG);
    if (img == '/img/roverMars.png') {
        setUniqueImgRover(elementIMG)
    }
}

const setUniqueImgRover = (actualImgRover) =>{
    let rovers = document.querySelectorAll('.img-rover');
        rovers.forEach((item)=>{
            if (actualImgRover != item){
                item.remove();
            }
        });
}

const showDirectionRover = (direction)=>{
    const arrow = document.querySelector('#direction-arrow');
    let rotation = '0';
    switch (direction) {
        case 'N':
            rotation = '0';
            break;
        case 'E':
            rotation = '90deg';
            break;
        case 'S':
            rotation = '180deg';
            break;
        case 'W':
            rotation = '270deg';
            break;
    }

    arrow.style.transform = `rotate(${rotation})`;
}

const getObstacles = (MAP) => {
    let { id } = MAP;
    fetch(`/obstacle/${id}`)
        .then(response => response.json())
        .then(obstacles => {
            obstacles.forEach(obstacle => {
                setElementOnMap(obstacle, '/img/rockMarte.png');
            });
        })
        .catch(error => {
            console.error('Error fetching the obstacles:', error);
        });
}
const processCommand = (command) => {
    document.querySelector('#error-message').style.display = 'none';
    document.querySelector('#error-message').innerHTML = '';
        let data = {
            command: command
        }
        fetch('/rover', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => response.json())
            .then(data => {
                if ('id' in data){
                        setElementOnMap(data, '/img/roverMars.png');
                        showDirectionRover(data.direction);
                }else{
                    displayErrorMessage(data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
}
const getCurrentPositionRover = () => {
    fetch('/rover').then(response => response.json())
        .then(data => {
            setElementOnMap(data, '/img/roverMars.png');
            showDirectionRover(data.direction);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

const displayErrorMessage = (message) => {
    var errorMessageDiv = document.getElementById('error-message');
    errorMessageDiv.innerText = message;
    errorMessageDiv.style.display = 'block'; 
}

const hideErrorMessage = () => {
    var errorMessageDiv = document.getElementById('error-message');
    errorMessageDiv.style.display = 'none'; 
}
getMap();
getCurrentPositionRover();

