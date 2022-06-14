package com.moon.asm.adapter;

/**
 * @author yujiangtao
 * @date 2018/7/24 18:26
 */
public class RemoveMethodAdapter /*extends ClassVisitor*/ {
    /*private String mName;
    private String mDesc;
    public RemoveMethodAdapter(ClassVisitor classVisitor, String mName, String mDesc) {
        super(ASM4, classVisitor);
        this.mName = mName;
        this.mDesc = mDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(name.equals(mName) && desc.equals(mDesc)) {
            // do not delegate to next visitor -> this removes the method
            return null;
        }
        return cv.visitMethod(access, name, desc, signature, exceptions);
    }*/
}
