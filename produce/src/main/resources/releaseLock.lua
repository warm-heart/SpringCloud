local key = KEYS[1]
local value = ARGV[1]

if value == redis.call('get',key)
then
--解锁
  redis.call('del',key)
  return 1
else
-- 解锁失败
  return 0
end
