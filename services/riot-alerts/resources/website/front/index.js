window.onload = function () {
    const source = new EventSource('/stream');
    
    source.onmessage = (event) => {
        const message = JSON.parse(event.data);
    
        message.riotCitizens.forEach(cit => {
            const row = document.createElement('tr');
            const time = document.createElement('td');
            const name = document.createElement('td');
            const peaceScore = document.createElement('td');
            const location = document.createElement('td');
    
            const datetime = new Date(message.dateTime.replaceAll('-', '/'));
            time.textContent = `${datetime.getHours()}:${datetime.getMinutes()}:${datetime.getSeconds()}`;
            name.textContent = cit.name;
            peaceScore.textContent = cit.peaceScore;
            location.textContent = message.location;
            
            row.appendChild(time);
            row.appendChild(name);
            row.appendChild(peaceScore);
            row.appendChild(location);
    
            document.getElementById("alerts-table").appendChild(row);
    
            alert(`Riot alert for ${cit.name}!`); 
        });
    };
}
