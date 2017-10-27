import { AsyncStorage } from 'react-native'


export function adaptUser {

	if (fetchToken) {

		fetch('https://www.mixcloud.com/oauth/access_token?client_id=' + CLIENT_ID + '&redirect_uri=http://www.example.com&client_secret=' + CLIENT_SECRET, options)
          .then((response) => response.json())
          .then((body) => {
           
          })
          .catch((error)=> {
            return null
          });
	}
}


export async function fetchToken() {

    return await AsyncStorage.getItem('@Podbean:token')
}
