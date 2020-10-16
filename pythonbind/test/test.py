import os
import sys
if True:
    thisPath = os.path.abspath(os.path.dirname(__file__))
    targetPath = '/../../pythonbind/target'
    sys.path.append(thisPath+targetPath)
    import pythonapi


patterns = ['@NonNull String userName',
            'String password',
            '@NonNull String host',
            'int port']
args = ['admin', '123456', '172.16.20.212', 7682]
print(pythonapi.matchArgument(patterns, args))
print(pythonapi.convertArgument(patterns, args))

patterns = ["@NonNull String[] hardwareSetsToBeRestarted"]
args = [['DAF013']]
print(pythonapi.matchArgument(patterns, args))
print(pythonapi.convertArgument(patterns, args))

pythonapi.getInstance('admin', '123456', '172.16.20.212', 7682)
pythonapi.getQPUInfo()
pythonapi.restartHardware(['DAF013'])
