package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Sacky extends GameObject{

    public float jumpForce;

    public void init(){
        jumpForce = 300;
        System.out.println("Hello from init().");
    }

    public void main(){
        if (Bdx.keyboard.keyHit("space"))
            applyForce(0, 0, jumpForce);
            
        if (Bdx.keyboard.keyHit("right"))
            jumpForce += 100;
    }
    
}