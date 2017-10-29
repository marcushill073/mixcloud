
export class User {

}

User.schema = {
  name: 'User',
  properties: {
    cloudcast_count: 'int',
    favorite_count: 'int',
    follower_count: 'int',
    following_count: 'int',
    is_premium: 'bool',
    key: 'string',
    name: 'string',
    pictures: {type: 'Pictures'},
    url: 'string',
    username: 'string'
  }
}

export class Pictures {

}

Pictures.schema = {
  name: 'Pictures',
  properties: {
    extra_large: 'string',
    large: 'string',
    medium:'string',
    medium_mobile: 'string',
    small: 'string',
    thumbnail: 'string'

  }
}

export function parseBody(body) {
  const pictures = {
    extra_large: body.pictures.extra_large,
    large: body.pictures.large,
    medium: body.pictures.medium,
    medium_mobile: body.pictures.medium_mobile,
    small: body.pictures.small,
    thumbnail: body.pictures.thumbnail
  }
  const user = {
    cloudcast_count: body.cloudcast_count,
    favorite_count: body.favorite_count,
    follower_count: body.follower_count,
    following_count: body.following_count,
    is_premium: body.is_premium,
    key: body.key,
    name: body.name,
    pictures: pictures,
    url: body.url,
    username: body.username
  }

  return user;
}
