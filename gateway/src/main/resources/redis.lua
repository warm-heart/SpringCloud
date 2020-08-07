local key = KEYS[1]
local value = ARGV[1]

if redis.call('get',key)
then
--如果存在 key 返回1
  return 1
else
-- 如果不存在key 返回0
  return 0
end
