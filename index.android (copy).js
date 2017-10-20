/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import { AppRegistry, View, NativeModules, StyleSheet, Image, Button } from 'react-native';
import SC from 'react-native-rest-client';
import TextBox from './js/components/TextBox';

export default class mixcloud extends Component {
  constructor(props) {
    super(props);

  };

  login =() => {
    SC.
  }

  render() {
    let userText;
    let passwordText;
    return (
      <View style={styles.container}>
          <Image  source={require('./js/assets/iconmonstr-soundcloud-2-240.png')}/>
        <View style={styles.form}>
          <TextBox src={require('./js/assets/iconmonstr-user-6-48.png')}
          ref={component => this._userText = component}/>
          <TextBox src={require('./js/assets/iconmonstr-key-2-48.png')}
          ref={component => this._passwordText = component}/>
        </View>
        <Button title='Log In'
            onPress={()=>{let user = this.getUserName();
            console.log(user)}}
            style={styles.button}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
      flex: 1,
      flexDirection: 'column',
      paddingTop: 20,
      alignItems: 'center',
      paddingBottom: 20
    },
  form: {
    flex: 2,
    flexDirection: 'column',
    alignSelf: 'center',
    marginTop: 20,
    marginBottom: 20
  },button: {
    flex: 3,
    color: '#ffb92e',
    flexDirection: 'column',
    alignContent: 'center',
    alignItems: 'center',
    alignSelf: 'stretch',
    marginTop: 20,
    marginBottom: 20
  },

});

AppRegistry.registerComponent('mixcloud', () => mixcloud);
