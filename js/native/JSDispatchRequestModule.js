import React, { Component } from 'react';
import { AppRegistry, AsyncStorage } from 'react-native';
import { adaptUser } from Adapters;

export function fetchUser(id, callback) {

	adaptUser()
	.then(() => {callback.success(id)})
	.catch(callback.error)

}

exports.module = JSDispatchRequestModule;
