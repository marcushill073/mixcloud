/*
*
* @providesModule Adapter
* @flow
*/
const BASE_URL='https://api.mixcloud.com/marcushill073/';

export default class Adapter {

  adaptUser(token) {

    fetch(BASE_URL + '?access_token=' + token)
    .then((response) => response.json())
    .then((body) => {
      console.log(body);
    })
    .catch((error)=> {
      console.log(error)
    });
  }

}

module.exports = new Adapter();
