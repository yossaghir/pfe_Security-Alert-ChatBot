import telepot
import sys
from pprint import pprint
Admin = str(sys.argv[3])
fichier = './ids/admin/' + Admin +'.txt'
g = open(fichier,'r')
idadmin = int(g.readline())
g.close()
f = open('./ids/token.txt','r')
stri = f.readline()
f.close()
bot = telepot.Bot(stri)
bot.sendMessage(idadmin, 'Commande *' + str(sys.argv[1]) + '* Trouvee, nombre de fois : *' + str(sys.argv[2]) +'*', parse_mode= 'Markdown')


