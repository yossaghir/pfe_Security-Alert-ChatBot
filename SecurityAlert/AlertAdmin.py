# coding=utf-8
import sys
import telepot
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
bot.sendMessage(idadmin, 'L\'utilisateur *' + str(sys.argv[2]) + '* a executé cette commande : *' + str(sys.argv[1]) + '*.', parse_mode= 'Markdown')
bot.sendMessage(idadmin, 'Pas de id pour cet utilisateur.')
