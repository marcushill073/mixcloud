/*
*
* @providesModule Adapter
* @flow
*/

import { User, Pictures } from '../schema/User'
const BASE_URL='https://api.mixcloud.com/marcushill073/';
const Realm = require('realm');

export default class Adapter {

  adaptUser(token) {

    fetch(BASE_URL + '?access_token=' + token)
    .then((response) => response.json())
    .then((body) => {
      Realm.open({schema: [User, Pictures]})
      .then(realm => {
        realm.write(() => {
        const picture = realm.create('Pictures', {
          extraLarge: body.pictures.extra_large,
          large: body.pictures.large,
          medium: body.pictures.medium,
          mediumMobile: body.pictures.medium_mobile,
          small: body.pictures.small,
          thumbnail: body.pictures.thumbnail
        });
        const user = realm.create('User', {
          cloudcastCount: body.cloudcast_count,
          favoriteCount: body.favorite_count,
          followerCount: body.follower_count,
          followingCount: body.following_count,
          isPremium: body.is_premium,
          key: body.key,
          name: body.name,
          pictures: picture,
          url: body.url,
          username: body.username
        });
      });
      })
    })
    .catch((error)=> {
      console.log(error)
    });
  }

}

module.exports = new Adapter();
