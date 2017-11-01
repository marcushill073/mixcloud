
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
}

export class User {}

User.schema = {
  name: 'User',
  properties: {
    cloudcastCount: 'int',
    favoriteCount: 'int',
    followerCount: 'int',
    followingCount: 'int',
    isPremium: 'bool',
    key: {type: 'string', optional: true},
    name: {type: 'string', optional: true},
    pictures: {type: 'Pictures'},
    url: {type: 'string', optional: true},
    username: {type: 'string', optional: true}
  }
}
