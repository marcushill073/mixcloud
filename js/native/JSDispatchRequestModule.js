 /*
 *
 * @providesModule JSDispatchRequestModule
 * @flow
 */
 'use strict';

 import React, { Component } from 'react';
 import { AppRegistry, NativeModules, AsyncStorage } from 'react-native';
import Adapter from 'Adapter';

export default class JSDispatchRequestModule {

 fetchUser() {
  this.fetchToken()
  .then((token) => { Adapter.adaptUser(token) })
  	.then(() => {NativeModules.DispatchRequestModule.onSuccess(id)})
  	.catch((error) => {NativeModules.DispatchRequestModule.onError(id, error)})

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
