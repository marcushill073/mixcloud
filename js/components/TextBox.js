import React, { Component } from 'react';
import { AppRegistry, TextInput, View, Image, StyleSheet } from 'react-native';

export default class TextBox extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: ''
    };
};
     getText() {
        return this.state.text;
    };


  render() {
    return (
    <View style={styles.container}>
      <Image style={styles.image}
        source={this.props.src}/>
       <TextInput style={styles.form}
              onChangeText={(text) => this.setState({text})}
              value={this.state.text}
            />
    </View>
  );
  }
}

const styles = StyleSheet.create({
  container: {
        flexDirection:'row',
        alignItems:'flex-start',
    },
    image: {
      height: 48,
      width: 48,
      flexDirection: 'row',
      backgroundColor: '#fff'
    },
  form: {
    flexDirection: 'row',
    margin: 5,
    height: 48,
    width: 200
  }
  });
