/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import { AppRegistry, WebView, AsyncStorage, NativeModules } from 'react-native';

const CLIENT_ID = '85yqWZ5zrbRGnN2tkA';
const CLIENT_SECRET = 'VMXPueW6p2p8zdf5gn7JWjrt5JvFvT2p';
const URL = 'https://www.mixcloud.com/oauth/authorize?client_id=' + CLIENT_ID + '&redirect_uri=http://www.example.com';

export default class mixcloud extends Component {
  constructor(props) {
    super(props);
 // Opens the url in the default browser

  }

  
  render() {
    let token = false;
    let code;
    return(
      <WebView source={{uri: URL}}
        userAgent='Chrome'
        onNavigationStateChange={(prevState, nextState, action) => {
          console.log(prevState);

          var url = require('url');
          var uri = url.parse(prevState.url, true);


          if(uri && uri.query && uri.query.code && !token) {

          var formData = new FormData();
          formData.append('code', uri.query.code);

          var options = {
              method:'POST',
              body: formData
          };

         
          fetch('https://www.mixcloud.com/oauth/access_token?client_id=' + CLIENT_ID + '&redirect_uri=http://www.example.com&client_secret=' + CLIENT_SECRET, options)
          .then((response) => response.json())
          .then((body) => {
            AsyncStorage.setItem('@Mixcloud:token', body.access_token);
          NativeModules.JSViewHelperModule.startNativeView('home');
          token=true;
          })
          .catch((error)=> {
            console.log(error)
          });

        }
        
      }
    }/>);
  }

}

async function fetchToken() {

            return await AsyncStorage.getItem('@Podbean:token')
          }
  

AppRegistry.registerComponent('mixcloud', () => mixcloud);
