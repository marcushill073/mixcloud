
export class User {}

User.schema = {
  name: 'User',
  properties: {
    cloudcastCount: 'int',
    favoriteCount: 'int',
    followerCount: 'int',
    followingCount: 'int',
    isPremium: 'bool',
    key: 'string',
    name: 'string',
    pictures: {type: 'Pictures'},
    url: 'string',
    username: 'string'
  }
}

export class Pictures {}

Pictures.schema = {
  name: 'Pictures',
  properties: {
    extraLarge: 'string',
    large: 'string',
    medium:'string',
    mediumMobile: 'string',
    small: 'string',
    thumbnail: 'string'

  }
}
