/*
*
* @providesModule Adapter
* @flow
*/

import { User, Pictures } from '../schema/User'

export default class Adapter {

  adaptUser(body) {
    var Realm = require('realm');

      Realm.open({schema: [User, Pictures]})
      .then((realm) => {

        realm.write(() => {
        const picture = realm.create('Pictures', {
          extraLarge: body.pictures.extra_large,
          large: body.pictures.large,
          medium: body.pictures.medium,
          mediumMobile: body.pictures.medium_mobile,
          small: body.pictures.small,
          thumbnail: body.pictures.thumbnail
        });
      });

        realm.write(() => {
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
    .catch((error)=> {
      console.log(error)
    });
  }

}

module.exports = new Adapter();
