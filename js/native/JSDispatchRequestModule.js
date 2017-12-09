 /*
 *
 * @providesModule JSDispatchRequestModule
 * @flow
 */
 'use strict';

 import React, { Component } from 'react';
 import { AppRegistry, NativeModules, AsyncStorage } from 'react-native';
import Adapter from 'Adapter';

const BASE_URL='https://api.mixcloud.com/marcushill073/';


export default class JSDispatchRequestModule {

 fetchUser() {
  this.fetchToken()
  .then((token) => {
    fetch(BASE_URL + '?access_token=' + token)
    .then((response) => response.json())
    .then((body) => {Adapter.adaptUser(body)})
  })
  .catch((error) => {console.log(error)});


}

 handleRequest(event) {
		if(event = 'USER') {
      this.fetchUser();
    }
}

async fetchToken() {

  return await AsyncStorage.getItem('@Mixcloud:token')
}

};

module.exports = new JSDispatchRequestModule();
