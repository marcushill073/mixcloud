

export class Pictures {}
Pictures.schema = {
  name: 'Pictures',
  properties: {
    extraLarge: {type: 'string', optional: true},
    large: {type: 'string', optional: true},
    medium: {type: 'string', optional: true},
    mediumMobile: {type: 'string', optional: true},
    small: {type: 'string', optional: true},
    thumbnail: {type: 'string', optional: true}
  }
};

export class User {}
User.schema = {
  name: 'User',
  properties: {
    cloudcastCount: {type: 'int', default:0},
    favoriteCount: {type: 'int', default:0},
    followerCount: {type: 'int', default:0},
    followingCount: {type: 'int', default:0},
    isPremium: 'bool',
    key: {type: 'string', optional: true},
    name: {type: 'string', optional: true},
    pictures: {type: 'Pictures'},
    url: {type: 'string', optional: true},
    username: {type: 'string', optional: true}
  }
};
