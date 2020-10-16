
import jpype
import jpype.imports
from jpype.types import *
import os

def matchArgument(patterns, args):
    if len(patterns) != len(args):
        return False
    for p, v in zip(patterns, args):
        if p.startswith('@NonNull ') and v == None:
            return False
        if p.startswith('@NonNull '):
            p = p[9:]
        vtype = p.split(' ')[-2]
        if vtype[-2:] == '[]' and not isinstance(v, list):
            return False
        if vtype[-4:] == '[][]' and not isinstance(v[0], list):
            return False
    return True

def convertArgument(patterns, args):
    typemap = {
        'boolean': JBoolean,
        'byte': JByte,
        'char': JChar,
        'short': JShort,
        'int': JInt,
        'long': JLong,
        'float': JFloat,
        'double': JDouble,
        'String': JString,

        'boolean[]': JArray(JBoolean),
        'byte[]': JArray(JByte),
        'char[]': JArray(JChar),
        'short[]': JArray(JShort),
        'int[]': JArray(JInt),
        'long[]': JArray(JLong),
        'float[]': JArray(JFloat),
        'double[]': JArray(JDouble),
        'String[]': JArray(JString),

        'boolean[][]': JArray(JArray(JBoolean)),
        'byte[][]': JArray(JArray(JByte)),
        'char[][]': JArray(JArray(JChar)),
        'short[][]': JArray(JArray(JShort)),
        'int[][]': JArray(JArray(JInt)),
        'long[][]': JArray(JArray(JLong)),
        'float[][]': JArray(JArray(JFloat)),
        'double[][]': JArray(JArray(JDouble)),
        'String[][]': JArray(JArray(JString)),

        'boolean[][][]': JArray(JArray(JArray(JBoolean))),
        'byte[][][]': JArray(JArray(JArray(JByte))),
        'char[][][]': JArray(JArray(JArray(JChar))),
        'short[][][]': JArray(JArray(JArray(JShort))),
        'int[][][]': JArray(JArray(JArray(JInt))),
        'long[][][]': JArray(JArray(JArray(JLong))),
        'float[][][]': JArray(JArray(JArray(JFloat))),
        'double[][][]': JArray(JArray(JArray(JDouble))),
        'String[][][]': JArray(JArray(JArray(JString))),
    }
    args2 = []
    for p, v in zip(patterns, args):
        vtype = p.split(' ')[-2]
        args2.append(typemap[vtype](v))
    return args2


jpype.startJVM(classpath=[os.path.abspath(os.path.dirname(
    __file__))+'/../../rpcapi/target/qos-rpcapi-1.0.jar'])
# jpype.startJVM(classpath=[os.path.abspath(
#     os.path.dirname(__file__))+'/../../rpcapi/target/classes'])

QOSServerApi = jpype.JClass('qc.qos.rpcapi.qpx1701.QOSServerApi')


class g:
    Instance = None


def getInstance(*args):
    patterns = ['@NonNull String userName',
                'String password',
                '@NonNull String host',
                'int port']
    if matchArgument(patterns, args):
        args = convertArgument(patterns, args)
        g.Instance = QOSServerApi(*args)
        return
    patterns = ['@NonNull String userName',
                'String password',
                '@NonNull String host',
                'int port',
                'boolean useSSL',
                'String keyFile',
                'char[] keyStorePassword']
    if matchArgument(patterns, args):
        args = convertArgument(patterns, args)
        g.Instance = QOSServerApi(*args)
        return
    raise TypeError('no input pattern match')
