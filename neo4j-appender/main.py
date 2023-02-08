from py2neo import Graph, Node, Relationship
from py2neo.matching import *


g = Graph("bolt://localhost:7687", auth=("neo4j", "bitnami1"))

# nodes = NodeMatcher(g)
# keanu = nodes.match("Person", name="Bob").all()
# print(keanu)
#
# tx = g.begin()
# a = Node("Person", name="Alice")
# tx.create(a)
# b = Node("Person", name="Bob")
# ab = Relationship(a, "KNOWS", b)
# tx.create(ab)
# g.commit(tx)




# nicole = Node("Person", name="Nicole", age=24)
# drew = Node("Person", name="Drew", age=20)
#
# mtdew = Node("Drink", name="Mountain Dew", calories=9000)
# cokezero = Node("Drink", name="Coke Zero", calories=0)
#
# coke = Node("Manufacturer", name="Coca Cola")
# pepsi = Node("Manufacturer", name="Pepsi")
#
# g.create(nicole | drew | mtdew | cokezero | coke | pepsi)
#
# g.create(Relationship(nicole, "LIKES", cokezero))
# g.create(Relationship(nicole, "LIKES", mtdew))
# g.create(Relationship(drew, "LIKES", mtdew))
# g.create(Relationship(coke, "MAKES", cokezero))
# g.create(Relationship(pepsi, "MAKES", mtdew))


ab = Relationship(Node("Person", name="Nicole", age=24), "LIKES", Node("Drink", name="Mountain Dew", calories=9000))
res = g.exists(ab)
print(res)

