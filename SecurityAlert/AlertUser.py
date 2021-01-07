# coding=utf-8
import telepot
import time
from pprint import pprint
from telepot.loop import MessageLoop
import sys
import os,signal
import time
Admin = str(sys.argv[4])
fichier = './ids/admin/' + Admin +'.txt'
g = open(fichier,'r')
idadmin = int(g.readline())
g.close()
f = open('./ids/token.txt','r')
stri = f.readline()
f.close()
bot = telepot.Bot(stri)
k=0
com=str(sys.argv[1])
user=str(sys.argv[2])
id1=int(sys.argv[3])
fichier='analyseLin/count/' + user
f=open(fichier,'r+')
j=f.read()
i=int(j)
f.close()
if i>=3:
    exit()
bot.sendMessage(int(id1), 'Est-ce que tu as executé cette commande *' + com + '*? Réponse par *oui* ou *non* seulement', parse_mode= 'Markdown')
oldtime = time.time()
updates = bot.getUpdates()
if updates:
    last_update_id = updates[-1]['update_id']
    bot.getUpdates(offset=last_update_id+1)
def handle(msg):
    content_type, chat_type, chat_id, = telepot.glance(msg)
    print(content_type, chat_type, chat_id)
    if content_type == 'text':
        global i
        global k
        stri = msg['text'].lower()
        if stri == 'oui' : 
            bot.sendMessage(chat_id, 'Votre réponse est enregistrée.')
            f=open(fichier,'w+')
            i=i+1
            f.write('{}'.format(i))
            f.close()
            print(i)
            #os.kill(os.getpid(), signal.SIGKILL)
            if i >= 3 :
                bot.sendMessage(chat_id, 'Arrêt d\'envoyer des alertes pour 30 min.')
                i=0
                time.sleep(30*60)
                f=open(fichier,'w+')
                f.write('{}'.format(i))
                f.close()
                k=1
            else:
                k=1
        elif stri == 'non' :
            global idAdmin
            f=open(fichier,'w+')
            i=0
            f.write('{}'.format(i))
            f.close()
            k=1
            print('i = ',i)
            bot.sendMessage(chat_id, 'On va reporter cette réponse à l\'administrateur.')
            bot.sendMessage(idadmin, 'L\'utilisateur *' + user + '* à confirmer qu\'il n\'a pas executer cette commande: *' + com + '*.', parse_mode= 'Markdown')
        else :
            bot.sendMessage(chat_id, 'Réponse par oui ou non seulement')
MessageLoop(bot, handle).run_as_thread()
print ('Listening ...')
while k==0:
    time.sleep(1)
    if time.time() - oldtime > 60:
        print ("it's been a minute")
        upd = bot.getUpdates()
        if not upd:
            print('no response')
            bot.sendMessage(idadmin, 'L\'utilisateur *' + user + '* à executer cette commande: *' + com + '*.', parse_mode= 'Markdown')
            bot.sendMessage(idadmin, 'Pas de confirmation par l\'utilisateur aprés 5 minutes d\'attente', parse_mode= 'Markdown')
            exit()
