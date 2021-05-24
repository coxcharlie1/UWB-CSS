*-----------------------------------------------------------
* Title      : Dissasembler
* Written by :Cloud Guys
* Date       :
* Description:user enters starting and end address and instructions
*             will be decoded
*-----------------------------------------------------------
    ORG    $1000
START:                  ; first instruction of program
        lea Counter,A0 *counts 32 line for a page of printing
        
        Lea Welcome,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        ADDI.B  #1,(A0)  *increments line counter
        
        Lea AddrSpec,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        ADDI.B  #1,(A0)  *increments line counter
        
        Lea Return,A1 ; load message into A1
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        ADDI.B  #1,(A0)  *increments line counter
        
        MOVEA.L  #$3000,A2  *start of source file
        MOVEA.L  #$FFFF,A3  *end of source file
            
StartingAddress

        Lea StartAddress_out,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        ADDI.B  #1,(A0)  *increments line counter
        
        LEA     AddrStart,A5    *stores string var at A5
        
        move.b  #0,d3            *loop counter
        move.b  #1,d4            *to keep track of addresses
              
       
        jsr     GetAddress
        ADDI.B  #1,(A0)  *increments line counter
        
        Lea Return,A1 ; load message into A1
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        ADDI.B  #1,(A0)  *increments line counter
        
        MOVE.L D2,A4           *move entered address to A4
        jsr     compare_address
        
        MOVEA.L A4,A2           *move new starting address to A2
            
EndingAddress
        
        clr.l   d3      ; clr d3
        clr.l   d4      ; clr d4  
        move.b  #2,d4            *to keep track of addresses, on ending address
        
        
        LEA     AddrEnd,A5    *stores string var at A5
        
        Lea Return,A1 ; load message into A0
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        
        Lea EndAddress_out ,A1 ; load message into A1
        move.b  #14,D0 ; load 14 into D0
        Trap #15       ; display message
        
        Lea AddrStart,A1 ; load message into A0
        move.b  #14,D0 ; load 14 into D0
        Trap #15       ; display message
        
        
        LEA     End_out,A1
        MOVE.B  #13,D0          *displays output for start addr        
        TRAP    #15
        ADDI.B  #1,(A0)  *increments line counter
        
        move.b  #2,d4           *currently on ending address
        
        
        JSR     GetAddress
        ADDI.B  #1,(A0)  *increments line counter
        
        Lea Return,A1 ; load message into A1
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        ADDI.B  #1,(A0)  *increments line counter
        
        MOVE.L D2,A4            *move entered address to A4
        
        JSR     compare_address
        
        MOVEA.L A4,A3           *move new ending address to A3
       
        bra     addr_setup
        
GetAddress 
        cmp     #8,d3   ; if counter at d4 is at 8, then done
        beq     DONE
        
        MOVE.B  #5,D0           *get current char value inputted
        TRAP    #15
        
        CMP.B   #$D,D1          *if its the carriage return character
        BEQ     ERROR_Address   *error address: not an 8 digit address
        
        MOVE.B  D1,(A5)+
              
        JSR     NUM_HEX        *Go to number test
        LSL.L   #4,D2           *shift over left 4 bits              
        ADD.B   D1,D2           *adds next value to temp register
        ADDQ.L  #1,D3           *increment loop counter
        
        bra GetAddress     ; branch back up to address


compare_address

        divs.w  #2,d2          *divide by 2
        lsr.l   #8,d2          *shifts out the quotient
        lsr.l   #8,d2
        
        CMP.B   #0,d2          *compares the remainder with 0
        BGT     ERROR_Address     *error address: not even
                               
        CMPA.L  A2,A4          *compares entered address to starting boundary
        BLT     ERROR_Address      *error address: less than starting boundary
        
        CMPA.L  A3,A4          *compares entered address to ending boundary
        BGT     ERROR_Address      *error address: greater than ending boundary
       
 
        MOVE.B  #0,(A5)        *add the carriage return 
        RTS
        
ERROR_Address

        Lea AddrErrOut,A1 ; load message into A1
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        ADDI.B  #1,(A0)  *increments line counter
        
        CMP.B   #1,D4           *compare address currently on
        BEQ     StartingAddress
        BRA     EndingAddress       
               


NUM_HEX                         *coverts char to hex where value is in D1
      
        CMPI.B  #$30,D1         *compares ASCII value ranges for numbers to input
        CMP.B   #$39,D1
        BGT.B   ALPHA_HEX     *if it is not a number considered in the range
        SUBI.B  #$30,D1        *gets the number from ASCII input
       
        RTS
       
ALPHA_HEX                      *converts char to hex where value is in D1 
        sub.b   #$37,d1 ; subtract 37 from d2
        
        rts    ; branch to organize
        
NUM_CHAR                       *converts hex to char where value is in D3
      
        CMPI.B  #$0,D3         *compares ASCII value ranges for numbers to input
        CMP.B   #$9,D3
        BGT.B   ALPHA_CHAR     *if it is not a number considered in the range
        ADDI.B  #$30,D3        *gets the number from ASCII input
       
        RTS
        
ALPHA_CHAR                      *converts hex to char where value is in D3
      
        ADDI.B  #$37,D3         *gets the letter from ASCII input
        RTS
        

DONE
        RTS                      
**************************************************************END OF IO PART******************************************************************
**************************************************************START OF DISSASSEMBLING ADDRESS*************************************************
DecodeComplete
        Lea Return,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        
        Lea AskContinueDecode,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        
        move.b  #5,D0      ; load 14 into D0
        Trap #15            ; display message
        
        cmp.b   #$4E,d1       *if 'N' branch to SIMHAULT
        beq     program_end
        
        cmp.b   #$59,d1       *if 'Y' restart then branch back to start
        beq     restart
        
        cmp.b   #$4E,d1       *if it's not 'N', ask again
        bne     DecodeComplete 
        
        cmp.b   #$59,d1       *if it's not 'Y', ask again 
        bne     DecodeComplete  
restart        *clrs registers for later in the program
        clr.l d0
        clr.l d1
        clr.l d2
        clr.l d3
        clr.l d4
        clr.l d5
        clr.l d6
        clr.l d7
        
        move.b  #0,(a0)  *this sets the counter back to zero, to start counting back up to 33 again

        movea.l $00000000,a0    *clrs registers for later in the program
        movea.l $00000000,a1
        movea.l $00000000,a2
        movea.l $00000000,a3
        movea.l $00000000,a4
        movea.l $00000000,a5
        movea.l $00000000,a6
        
        Lea Return,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        
        bra     Start

program_end
        SIMHALT             ; halt simulator
        
addr_setup *address print
        addi.b  #1,(a0) *adds one to our variable
    
        cmpi.b  #32,(a0)  *this will go to 32 in our program because there are 32 lines on a screen.
        beq     pause     *if equal to 33 branch to pause
        bra     addr_setup_continue  * branch past pause
    
pause 
        Lea Return,A1 ; load message into A1
        move.b  #13,D0      ; load 14 into D0
        Trap #15            ; display message
        
        Lea any_key_to_continue,A1 ; load message into A1
        move.b  #14,D0      ; load 14 into D0
        Trap #15            ; display message
        
        move.b  #1,(a0)  *this sets the variable back to zero, to start counting back up to 33 again
        move.b  #5,d0   * asks for a char from keyboard, and does nothing with it causing a pause in printing
        Trap    #15     
    
addr_setup_continue    *The rest of our code in addr_setup and beyond
        
        CMP.L   A3,A2   *test if we are at the last address
        BGT     DecodeComplete
        LEA AddrCurrent,A5
        clr.l   d1      ; clr d1, holds current address
        clr.l   d2      ; clr d2, holds current instruction
        clr.l   d3      ; clr d3, temp
        clr.l   d4      ; clr d4,loop counter 
        clr.l   d5      ; for shifting
        clr.l   d6
        clr.l   d7
        move.l  a2,d1
        MOVE.B  #28,d5
        
        Lea Return,A1 ; load message into A1
        move.b  #13,D0 ; load 14 into D0
        Trap #15 
        BRA     addr_convert
addr_convert
        CMP.B   #8,D4           *want to loop 8 times
        BEQ     addr_print
        MOVE.L  D1,D3           *move most significant number in address into temp
        LSL.L   #4,D1           *shift the bytes in D2
        LSR.L   D5,D3           *shift bytes in D3
        JSR     NUM_CHAR        *convert number in D3 to char equivalent
        
        MOVE.B  D3,(A5)+        *adds char to address variable
        ADDQ.L  #1,D4           *increment loop counter
        bra     addr_convert
               
addr_print
        MOVE.B  #$20,(A5)+       *add Space
        MOVE.B  #$00,(A5)
        *Lea Return,A1 ; load message into A1
        *move.b  #13,D0 ; load 14 into D0
        *Trap #15 
        
        
        Lea AddrCurrent,A1 ; load message into A1
        move.b  #14,D0 ; load 14 into D0
        Trap #15       ; display message
        
        clr.l   d0      ; clr d1, holds current address
        clr.l   d1      ; clr d1, holds current address
        clr.l   d2      ; clr d2, holds current instruction
        clr.l   d3      ; clr d3, temp
        clr.l   d4      ; clr d4,loop counter 
        clr.l   d5      ; for shifting 
*******************************************************END OF DISSASSEMBLING ADDRESS PART*****************************************************     

*******************************************************START OF DISSASSEMBLING OPCODE PART***************************************************** 
instruct_start
      
    MOVE.W  (A2)+,D1    *Moves SUB to D1 increment address for EA calculation
    MOVE.L  D1,D2       *Move instruction to D2
    
    DIVU.W  #$1000,D2   *divide instruction to get first digit
    
    cmp.b #6,d2
    beq     bcc_blt_bge_beq_bra
      
    swap    d1          * move address over, to free up storage space
    move.b  d2,d1       *move the size of move to d1
    move.w  #0000,d2    * just leave the part of the address that needs to be decoded
    
    swap    d2          * swap the part of the address that needs to be decoded
    bra     breakInstruction
*******************************************************START OF DISSASSEMBLING BRANCHING PART*************************************************    
*vvvvvvvvvvvvvvvvvvvvvvv BCC'S Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv 
bcc_blt_bge_beq_bra
     MOVE.W  #$0000, D2  *clears 6  
    SWAP    D2 ;        *gets corresponding condition bits
    LSR.L   #8,D2       *shifts over condition bits
    
    CMP.B   #$0,D2      *if it is BRA
    BEQ     BRA_OP
    
    CMP.B   #$4,D2      *if it is Bcc
    BEQ     Bcc_OP
    
    CMP.B   #$7,D2      *if it is BEQ
    BEQ     BEQ_OP
    
    CMP.B   #$D,D2      *if it is BLT
    BEQ     BLT_OP
    
    CMP.B   #$C,D2      *if it is BGE
    BEQ     BGE_OP
    
    BRA     invalid_branch *if it's any other branch instruction
    
invalid_branch
    clr.l   d2
    lea     invalid_data_print,a1
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    
    
    move.w  d1,d5
    clr.w   d1
    
    jsr     address
    move.b #3,d2
    jsr Convert_to_char
    
    lea     Return,a1   *CR, LF ********************************************************new
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    
    bra     addr_setup  *back up to main loop
    
    
BRA_OP
    lea     bra_out,a1
    move.b  #14,d0
    trap    #15
    BRA     branch_size
    *BRA     EABranch
    
Bcc_OP
    lea     bcc_out,a1
    move.b  #14,d0
    trap    #15
    BRA     branch_size
    *BRA     EABranch
    

BEQ_OP
    lea     beq_out,a1
    move.b  #14,d0
    trap    #15
    BRA     branch_size
    *BRA     EABranch

BLT_OP
    lea     blt_out,a1
    move.b  #14,d0
    trap    #15
    BRA     branch_size
    *BRA     EABranch

BGE_OP
    lea     bge_out,a1
    move.b  #14,d0
    trap    #15
    BRA     branch_size
    *BRA     EABranch
branch_size

    CMP.B   #$00,D1
    beq     Branch_word          *16 bit displacement if field starts with 00
    
    bra     Branch_byte          *else its byte, longs aren't going to be tested
    
Branch_word   
    addi.b  #01,d7 *so BranchAddressPrint knows if it needs to read in a word or not
    
    lea     _W,a1                 ;print '.B'
    move.b  #14,d0
    trap    #15 
    BRA     EABranch
    
Branch_byte
      lea     _B,a1                 ;print '.B'
      move.b  #14,d0
      trap    #15
      
      andi.w #$00FF,D1  *get last two digits of branch instruction
      
      bra EA_Branch_byte  
    
EABranch
    *LEA     BranchAddress,A4    *stores branch address string at A4
    CLR.L   D1
    CLR.L   D2
    MOVE.W  (A2),D1    *Moves instruction to D1 increment address for EA calculation
    *MOVE.W  A2,D2
EA_Branch_byte  
    LEA     BranchAddress,A4    *stores branch address string at A4 
    MOVE.W  A2,D2 
    ADD.W   D1,D2      *address is at D2
    CLR.L   D1
    MOVE.B  #0,D5       *loop counter
    MOVE.B  #28,D4
    MOVE.B  #$20,(A4)+ *moves space into address variable
    BRA     GetBranchAddr
    
GetBranchAddr
    CMP.B   #8,D1           *want to loop 8 times
    BEQ     BranchAddressPrint
    MOVE.L  D2,D3           *move most significant number in D2 into D3
    LSL.L   #4,D2           *shift the bytes in D2
    LSR.L   D4,D3           *shift bytes in D3
    JSR     NUM_CHAR        *convert number in D3 to char equivalent
    MOVE.B  D3,(A4)+        *adds char to address variable
    ADDQ.L  #1,D1           *increment loop counter
    BRA     GetBranchAddr 
    
BranchAddressPrint
        MOVE.B  #$00,(A4)
        lea     BranchAddress,a1
        move.b  #14,d0
        trap    #15
        
        cmp.b   #01,d7
        bne     addr_setup 
        MOVE.W  (A2)+,D1    *just to increment instruction
        bra     addr_setup       
        


*******************************************************END OF DISSASSEMBLING BCC'S*****************************************************

***************************************************************************************************************************************
*******************************************************START OF DISSASSEMBLING ALL OTHERS *************************************************  
breakInstruction
           
     addq.b  #1,d0 * a counter to determine which Dn to put the result of the andi below into
     move.l  d2,d3 * move d2 to d3 to be worked on before the shift
     lsr.l   #3,d2   * shift d2 over 3 to for the next calculation
     andi.w  #0007,d3    * find the byte of data. Either source mode/reg or dest mode/reg
     
     cmp.b   #1,d0   *if counter 1, byte is source reg
     beq     Data5
     cmp.b   #2,d0   *if counter 2, byte is source mode
     beq     Data4
     cmp.b   #3,d0   *if counter 3, byte is dest mode
     beq     Data6
     cmp.b   #4,d0   *if counter 4, byte is dest mode
     beq     Data7
     
Data5   *Source reg: D5
    move.b  d3,d5
    clr     d3
    bra     breakInstruction
Data4   *Source mode: D4
    move.b  d3,d4
    clr     d3
    bra     breakInstruction
    
Data6   *destination mode: D6(size)
    move.b  d3,d6
    clr     d3
    bra     breakInstruction
    
Data7   *destination register: D7
    move.b  d3,d7
    clr     d3 
    
     
    cmp.b #0,d1         ; branch to the correct part of code depending on what the most signifigant byte
    beq     invalid_opcode    *invalid opcode
    cmp.b #3,d1         
    ble     move    
    cmp.b #4,d1
    beq     movem_lea_jsr_rts_nop_mulsL
    cmp.b #5,d1
    beq     invalid_opcode    *invalid opcode    
    cmp.b #7,d1
    beq   invalid_opcode    *invalid opcode
    cmp.b #8,d1
    beq   invalid_opcode    *invalid opcode
    cmp.b #9,d1
    beq     sub
    cmp.b #$A,d1
    beq   invalid_opcode    *invalid opcode
    cmp.b #$B,d1
    beq   invalid_opcode    *invalid opcode
    cmp.b #$C,d1
    beq     mulsW_and
    cmp.b #$D,d1
    beq     add
    cmp.b #$E,d1
    beq     asr_lsl
    cmp.b #$F,d1
    bge   invalid_opcode    *invalid opcode
    

*vvvvvvvvvvvvvvvvvvvvvvv Move Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv 
move    
    cmp.b   #1,d6   *check for invalid opcodes
    beq     invalid_opcode
    cmp.b   #7,d6
    beq     move_invalid_check
    bra     move_size
    
move_invalid_check  *check for invalid opcodes
    cmp.b   #4,d7
    beq     invalid_opcode

move_size   *branch to the correct move size based on byte in d1
    cmp.b   #1,d1   ; if a move.b, branch and print below
    beq     moveB
    cmp.b   #2,d1   ; if a move.l, branch and print below
    beq     moveL
    cmp.b   #3,d1   ; if a move.w, branch and print below
    beq     moveW
    
    
moveB
    move.b  #1,d2   ; moves 3 to d2 so if reading in data EA print knows to read in a word.
    lea     move_b,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint *branches down for ea work to be printed on the same line
    

moveL
    move.b  #2,d2   ; moves 3 to d2 so if reading in data EA print knows to read in a word.
    lea     move_l,a1
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint *branches down for ea work to be printed on the same line

    
moveW  
    move.b  #3,d2   ; moves 3 to d2 so if reading in data EA print knows to read in a word.
    lea     move_w,a1
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint *branches down for ea work to be printed on the same line
    
*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv SUB Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvv
sub  
  
    cmp.b   #3,d6       * takes care of suba
    beq     invalid_opcode
    cmp.b   #7,d6       * takes care of suba
    beq     invalid_opcode
    
    cmp.b   #4,d6            *if ea - Dn -> ea
    beq     swap_sub
    
    cmp.b   #5,d6            *if ea - Dn -> ea
    beq     swap_sub
    
    cmp.b   #6,d6            *if ea - Dn -> ea
    beq     swap_sub
    
    bra     swap_reg         *checks to see what size
    
swap_sub
    
    cmp.b   #1,d4            *if it is subx
    ble     invalid_opcode
    
swap_reg    
    JSR     swap_registers
   
    cmp.b   #0,d2
    beq     subB
    cmp.b   #1,d2
    beq     subW
    cmp.b   #2,d2
    beq     subL
    cmp.b   #4,d2
    beq     subB
    cmp.b   #5,d2
    beq     subW
    cmp.b   #6,d2
    beq     subL
    

subB
    move.b  #1,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     sub_b,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint    ;branches down for ea work to be printed on the same line
    
subW
    move.b  #3,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     sub_w,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint    ;branches down for ea work to be printed on the same line

subL
    move.b  #2,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     sub_l,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint      ;branches down for ea work to be printed on the same line   

*vvvvvvvvvvvvvvvvvvvvvvv mulsW_and vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

mulsW_and 
    move.w  #$0000, d2  *clr out d2, d1 below
    move.b  #00,d1  
    swap    d1  *swap d1
    move.w  d1,d2   *move d1 to d2
    swap    d1      *swap d1 again
    lsr.l   #6,d2   *shift d2
    andi.b  #07,d2 ******might need to be andi.w #0007
    
    cmp.b   #7,d2   *cmp to see if muls or and
    beq     mulsW
    bra     and
    
    
**********************mulsW*******************   
mulsW
    cmp.b   #1,d4   *source cant be an address register
    beq     invalid_opcode

    clr.l   d6 ; sets d6 to 0's so EA print displays the correct result
    move.b  #3,d2   ; moves 3 to d2 so if reading in data EA print knows to read in a word.
    
    lea     muls_w,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint      ;branches down for ea work to be printed on the same line
    
    
********************************AND****************************
and
    cmp.b   #3,d6   *these check for invalid opcodes
    beq     invalid_opcode
    cmp.b   #7,d6
    beq     invalid_opcode
    
    JSR     swap_registers  *checks to see if registers need to be swapped
    cmp.b   #3,d2   *checks to see if registers are invalid
    blt     zero_d6
        
    cmp.b  #0, d6   *more invalid opcode checking after swap
    beq     invalid_opcode
    cmp.b  #1,d6
    beq    invalid_opcode
    cmp.b  #7,d6
    beq    and_data *more invalid checking
    bra    and_type
      
    
zero_d6
    cmp.b #1,d4 
    beq    invalid_opcode  *branch if invalid  
    bra     and_type
    
and_data
    cmp.b  #2,d7
    bge    invalid_opcode   *branch if invalid 
    
and_type    *finds the size of and based on d2
    cmp.b   #0,d2
    beq     andB
    cmp.b   #1,d2
    beq     andW
    cmp.b   #2,d2
    beq     andL
    cmp.b   #4,d2
    beq     andB
    cmp.b   #5,d2
    beq     andW
    cmp.b   #6,d2
    beq     andL
    

andB
    move.b  #1,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     and_b,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint    ;branches down for ea work to be printed on the same line
    
andW
    move.b  #3,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     and_w,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint    ;branches down for ea work to be printed on the same line

andL
    move.b  #2,d2   ; moves 1 to d2 so if reading in data EA print knows to read in a word.
    lea     and_l,a1    
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    bra     eaPrint      ;branches down for ea work to be printed on the same line
  
    

*^^^^^^^^^^^^^^^^^^^ movem_lea_jsr_rts_nop_mulsL Decoded ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  

movem_lea_jsr_rts_nop_mulsL

*************Bisrats Code Here***************
                andi.l  #$ffff0000,d1
                swap    d1
*vvvvvvvvvvvvvvvvvvvvvvv NOP Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv                 
NOP_OPCODE_IMPL                                 *NOP implementation 
                CMP.W     #$4E71,D1
                BNE       RTS_OPCODE_IMPL      
                LEA       NOP_OPCODE,A1
                JSR       PRINT_TO_SCREEN
                BRA       addr_setup
*vvvvvvvvvvvvvvvvvvvvvvv RTS Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv                
RTS_OPCODE_IMPL                                  *RTS implementation 
                CMP.W     #$4E75,D1
                BNE       OC_decode      
                LEA       RTS_OPCODE,A1
                JSR       PRINT_TO_SCREEN
                BRA       addr_setup
                
OC_decode   
                movem.l      d5/d4/d3,-(sp)  
                CLR.L       D5
                CLR.L       D4
                CLR.L       D3
                
                MOVE.W      D1,D5                   * move the original operation into D1
                MOVE.W      D1,D3                   * also move it into D7          
                ANDI.W      #$003F,D5               * EA SPLIT FOR JSR               
                *MOVE.B      D1,D5                   * move the last 6 bits into D5
                
                MOVE.W      D3,D4 
                ROR.W       #6,D4
                ANDI.W      #$0007,D4
                CMP.W       #%111,D4
                BEQ         LEA_OPCODE_IMPL
                
                CLR.L       D4
                LSL.W       #4,D3
                LSR.W       #4,D3                   * rotate the operation to the right by 6 bits
                LSR.W       #6,D3                   * move the new op value into D1
     
                ****NEW CODE BLOCKS FOR NOT COMPARING ****
                
                clr         d2
                MOVE.W      D3,D2
                LSR.w       #2,D2
                CMP.W       #%0110,D2
                BEQ         NOT_IMPLEMENTATION
                
                ****END *******
     
                *ANDI.W      #$0007,D7
                MOVE.B      D3,D4                   * move these bits into D4
            
                CMP.W       #%111010,D4
                BEQ         JSR_OPCODE_IMPL
                
                LSR.W       #1,D4
                CMP.W       #%11000,D4
                BEQ         MULS_OPCODE_IMPL
                
                clr         d3
                move.w      d5,d3
                lsr.w       #3,d3
                andi.b      #$7,d3
                CMP.b       #$000,d3
                BEQ         Invalid_code_back
                
                CMP.W       #%11001,D4
                BEQ         MOVEM_OPCODE_IMPL
                CMP.W       #%10001,D4
                BEQ         MOVEM_OPCODE_IMPL
                
                swap    d1                
                BRA          invalid_opcode 
                
        
        *********NOT BLOCKS*******
NOT_IMPLEMENTATION                      *NOT implementation                
                    CLR        D2
                    MOVE.W     D3,D2
                    ANDI.W     #$3,d2
 
                    CMP        #$2,D2
                    BEQ        NOT_Long
                    CMP        #$1,D2 
                    BEQ        NOT_WORD
                    CMP        #$0,D2 
                    BEQ        NOT_BYTE
                    BRA        Invalid_code_back      
NOT_Long  
        movem.l     (sp)+,d5/d4/d3
        LEA     NOTL_OPCODE,A1
        JSR     PRINT_TO_SCREEN
        LEA     DISShor_SPACE,A1
        JSR     PRINT_TO_SCREEN      
        BRA     NOT_EA
        
NOT_WORD
        movem.l     (sp)+,d5/d4/d3
        LEA     NOTW_OPCODE,A1
        JSR     PRINT_TO_SCREEN
        LEA     DISShor_SPACE,A1
        JSR     PRINT_TO_SCREEN      
        BRA     NOT_EA

NOT_byte
        movem.l     (sp)+,d5/d4/d3
        LEA     NOTB_OPCODE,A1
        JSR     PRINT_TO_SCREEN
        LEA     DISShor_SPACE,A1
        JSR     PRINT_TO_SCREEN      
        BRA     NOT_EA
        
NOT_EA
        JSR Source  ; jump to SR source for printing
        JSR sourceRegister  *jump to SR sourceRegister for printing
        JSR MoreNeededSource     
        BRA addr_setup    
                
                
   ********    END OF NOT *************

Invalid_code_back
                  swap    d1
                  BRA          invalid_opcode  
*vvvvvvvvvvvvvvvvvvvvvvv JSR Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv                   
JSR_OPCODE_IMPL  

                BNE       LEA_OPCODE_IMPL      
                LEA       JSR_OPCODE,A1
                JSR       PRINT_TO_SCREEN
                
                JSR       JSR_EA
                                  
           
JSR_EA     
             CLR         D6
             MOVE.W      D5,D6
             ROR.W       #3,D5                   * rotate the operation to the right by 3 bits
             ANDI.W      #$0007,D6               * GET THE REGISTER TYPE
             
             CMP.B       #%010,D5
             BEQ         JSR_ADDRESS
             CMP.B       #%111,D5
             BEQ         JSR_ABSOLUTE
             BRA         invalid_opcode
             RTS
                                 

JSR_ADDRESS  
            LEA         EA_TYPE,A5
            MOVE.B      #$28,(A5)+              * add "("  
            MOVE.B      #$41,(A5)+              * add "A" 
            ADD.B       #$30,D6                 * convert data register # to hex digit
            MOVE.B      D6,(A5)+                * register 
            MOVE.B      #$29,(A5)+              * ADD ")"  
            MOVE.B      #0,(A5)
            
            LEA         DISShor_SPACE,A1
            JSR         PRINT_TO_SCREEN
            
            LEA         EA_TYPE,A1 
            JSR         PRINT_TO_SCREEN
            BRA         addr_setup
           *SIMHALT      *TO DO                 

disp_hex    lea     disp_dollar_sign,a1
            jsr     print_to_screen
            rts

JSR_ABSOLUTE
             movem.l     (sp)+,d5/d4/d3  
             LEA         DISShor_SPACE,A1
             JSR         PRINT_TO_SCREEN     
             *LEA         DISP_DOLLAR_SIGN,A1
             *JSR         PRINT_TO_SCREEN 
             cmp.w       #$4EB9,D1
             BEQ         READ_LONG_ADDRESS 
             move.w     (A2)+,D5
             cmp.w      #$0000,D5
             bne        read_another 
             move.W     (A2)+,D5 
             
READ_LONG_ADDRESS   
             MOVE.L     (A2)+,D5
read_another                    
            jsr         convert_to_char
            bra         addr_setup
   
*vvvvvvvvvvvvvvvvvvvvvvv LEA Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv                 
LEA_OPCODE_IMPL                                     *LEA implementation starts 
                LEA       LEA_OPCODE,A1
                JSR       PRINT_TO_SCREEN
                
                LEA         DISPLAY_SPACE,A1
                JSR         PRINT_TO_SCREEN
                
                BRA       Leaea_Print         *To Do
                *SIMHALT             ; halt simulator to do
         

Leaea_Print
           movem.l     (sp)+,d5/d4/d3 
           JSR Source  ; jump to SR source for printing
           JSR sourceRegister  *jump to SR sourceRegister for printing
           JSR MoreNeededSource 
           
            LEA     DISPLAY_comma,A1
            JSR     PRINT_TO_SCREEN
            
            jsr     lea_ADDRESS
            bra     addr_setup 
            
LEA_ADDRESS  
            LEA         EA_TYPE,A5
            *MOVE.B      #$28,(A5)+              * add "("  
            MOVE.B      #$41,(A5)+              * add "A" 
            ADD.B       #$30,D7                 * convert data register # to hex digit
            MOVE.B      D7,(A5)+                * register 
            *MOVE.B      #$29,(A5)+              * ADD ")"  
            MOVE.B      #0,(A5)
            
            
            LEA         EA_TYPE,A1 
            JSR         PRINT_TO_SCREEN
            bra          addr_setup
*vvvvvvvvvvvvvvvvvvvvvvv MULS Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv          
MULS_OPCODE_IMPL    
                    movem.l     (sp)+,d5/d4/d3                                      
                    LEA   MULS_OPCODE,A1
                    JSR   PRINT_TO_SCREEN
                    LEA     DISshor_space,A1
                    JSR     PRINT_TO_SCREEN
                    BRA   eaPrint         
                    *SIMHALT                 *to do
    
*vvvvvvvvvvvvvvvvvvvvvvv MOVEM Decoded vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv                    
MOVEM_OPCODE_IMPL                                       *MOVEM implementation starts 
                 * movem.l     (sp)+,d5/d4/d3
                  LEA         MOVEM_OPCODE,A1
                  jsr         print_to_screen
                  LSR.W       #6,D1 
                  BTST        #$0,D1  
                  BNE         Long_movem
                  bra         word_movem                  

word_movem   
                  LEA         WORD_size,A1
                  jsr         PRINT_TO_SCREEN
                  LEA         DISPLAY_SPACE,A1
                  JSR         PRINT_TO_SCREEN
                  BTST        #$4,D1
                  BNE          mem_to_reg
                  bra          reg_to_mem
                  *bra         eaPrint
                  *SIMHALT 

Long_movem
              LEA         long_size,A1
              jsr         PRINT_TO_SCREEN
              
              LEA         DISPLAY_SPACE,A1
              JSR         PRINT_TO_SCREEN
              BTST        #$4,D1
              BNE          mem_to_reg
              bra          reg_to_mem
              *bra         eaPrint
  
            SIMHALT 
            
mem_to_reg
        CLR       D5
        clr       d2
        move.w    (A2)+,D5
        MOVE.l     D5,D2
        movem.l    (sp)+,d5/d4/d3 
        JSR Source  ; jump to SR source for printing
        JSR sourceRegister  *jump to SR sourceRegister for printing
        JSR MoreNeededSource    *jump to SR MoreNeededSource for printing
        LEA         DISPLAY_comma,A1
        JSR         PRINT_TO_SCREEN
        MOVE.l     D2,D5
        jsr   convert_to_char   
        bra addr_setup

reg_to_mem               
        move.w    (A2)+,D5
        jsr         convert_to_char  
        LEA         DISPLAY_comma,A1
        JSR         PRINT_TO_SCREEN
        movem.l    (sp)+,d5/d4/d3 
        JSR Source  ; jump to SR source for printing
        JSR sourceRegister  *jump to SR sourceRegister for printing
        JSR MoreNeededSource           
        bra addr_setup
                
PRINT_TO_SCREEN
                 MOVE.B      #14,D0
                 TRAP        #15
                 RTS 

*************Bisrats Code end here***************


**************** Tomomis Code ***************
*/////////////////// ADD /////////////////////
add

      cmp.b   #3,d6   ;             ;if d3 = 3, this is ADDA 
      beq     invalid_opcode        ;print invalid message
      cmp.b   #7,d6                 ;if d3 = 7, this is ADDA
      beq     invalid_opcode        ;print invalid message
      
      cmp.b   #2,d6
      ble     add_Dn_type
      cmp.b   #4,d6
      bge     add_EA_type
      
add_Dn_type  
      lea     _add,a1               ;print out "ADD"    
      move.b  #14,d0
      trap    #15

      ; <ea> + Dn -> Dn
      cmp.b   #0,d6   ;B            ;if d3 = 0, Byte and destination is Dn
      beq     add_data_byte     
      cmp.b   #1,d6   ;W            ;if d3 = 1, Word and destination is Dn
      beq     add_data_word
      cmp.b   #2,d6   ;L            ;if d3 = 2, Long and destination is Dn
      beq     add_data_long

add_EA_type
      ;ADDX
      cmp.b   #1,d4
      ble     invalid_opcode
      
      lea     _add,a1               ;print out "ADD"    
      move.b  #14,d0
      trap    #15
      
      ; Dn + <ea> -> <ea>  
      cmp.b   #4,d6   ;B            ;if d3 = 4, Byte and destination is EA
      beq     add_ea_byte       
      cmp.b   #5,d6   ;W            ;if d3 = 5, Word and destination is EA
      beq     add_ea_word
      cmp.b   #6,d6   ;L            ;if d3 = 6, Long and destination is EA
      beq     add_ea_long
 

add_data_byte
      jsr     print_byte            ;print out '.B'
      jsr     add_print_ea_for_data ;print out EA for 'ADD.B'
      
add_data_word
      jsr     print_word            ;print out '.W'
      jsr     add_print_ea_for_data ;print out EA for 'ADD.W'
  
add_data_long
      jsr     print_long            ;print out '.L'
      jsr     add_print_ea_for_data ;print out EA for 'ADD.L'

add_ea_byte
      jsr     print_byte            ;print out '.B'
      jsr     add_print_ea_for_ea   ;print out EA for 'ADD.B'
      
add_ea_word
      jsr     print_word            ;print out '.W'
      jsr     add_print_ea_for_ea   ;print out EA for 'ADD.W'
      
add_ea_long
      jsr     print_long            ;print out '.L'
      jsr     add_print_ea_for_ea   ;print out EA for 'ADD.L'

add_print_ea_for_data               ;print out EA when destination is Dn
      clr.l   d6
      jsr     eaPrint               ;print out EA for Dn + <ea> -> <ea>

add_print_ea_for_ea                 ;print out EA when destination is EA
      jsr     swap_registers        ;swap data registers 
      jsr     eaPrint               ;print out EA for Dn + <ea> -> <ea>

*/////////////////////// ASR & LSL /////////////////////////
asr_lsl
    move.l  d1,d2                   ;copy machine code to D2
    swap    d2                      ;get rid of opcode
    and.l   #$FFF,d2                ;get EA part (0-11)
    move.w  d2,d3                   ;copy D2 to D3
    lsr     #6,d3                   ;get 3-4 to check if it is memory or register (<ea> or Dn,Dn/#,Dn)
    and.l   #3,d3                   
    cmp.b   #3,d3                   
    beq     asr_lsl_mem             ;if D3 = 11, memory
    bra     asr_lsl_reg             ;if D3 != 11, register
    
asr_lsl_mem
    cmp.b   #2,d7
    bge     invalid_opcode          
    divu.w  #$100,d2
    cmp.b   #3,d2                   ;check if LSL or ASL
    beq     lsl_mem                 ;if D2 = 3, LSL
    bra     asr_mem                 ;if D2 != 3, ASR
    
asr_lsl_reg
    and.l   #3,d4
    cmp.b   #2,d4
    bge     invalid_opcode
    move.w  d2,d3
    and.l   #$F,d3
    lsr     #3,d3
    cmp.b   #0,d3                   ;check if LSL or ASL
    beq     asr_reg                 ;if D3 = 0, ASR
    bra     lsl_reg                 ;if D3 != 0, LSL

lsl_mem                             ;LSL memory shifting
    lsr     #2,d6
    cmp.b   #0,d6
    beq     invalid_opcode          ;if LSR, invalid opcode
    lea     lsl,a1                  ;print LSL
    move.b  #14,d0
    trap    #15
    bra     asr_lsl_mem_print_ea
    
asr_mem                             ;ASR memory shifting
    lsr     #2,d6
    cmp.b   #1,d6
    beq     invalid_opcode          ;if ASL, invalid opcode
    lea     asr,a1                  ;print ASR
    move.b  #14,d0
    trap    #15
    bra     asr_lsl_mem_print_ea    ;print EA for memory shifting 
    
asr_lsl_mem_print_ea                ;print EA for ASR, LSL memory shifting
    jsr     print_word              ;print .W
    swap    d2
    move.w  d2,d6
    and.l   #$3F,d6
    move.w  d6,d7
    lsr     #3,d6                   ;D6: mode
    and.l   #7,d7                   ;D7: register number
    
    move.l  #1,d7
    JSR Destination *jump to SR Destination for printing
    JSR destinationRegister *jump to SR destinationRegister for printing
    JSR MoreNeededDestination *jump to SR MoreNeededDestination for printing
    
    **********************************CR,LF,Back up to main loop
    bra addr_setup             *goes back up to addr_setup to start next address
                               *might have a branch here to do the enter tab functionality and 
                               *increment counter

asr_reg                             ;ASR register shifting
    lsr     #2,d6
    cmp.b   #1,d6
    beq     invalid_opcode          ;if ASL, invalid opcode
    lea     asr,a1                  ;print ASR
    move.b  #14,d0
    trap    #15 
    bra     asr_lsl_reg_print_ea    ;print EA for register shifting

lsl_reg                             ;LSL register shifting
    lsr     #2,d6
    cmp.b   #0,d6
    beq     invalid_opcode          ;if LSR, invalid opcode
    lea     lsl,a1                  ;print LSL       
    move.b  #14,d0
    trap    #15
    bra     asr_lsl_reg_print_ea    ;print EA for register shifting

asr_lsl_reg_print_ea                ;print EA for LSL, ASR register shifting
    move.w  d2,d3
    lsr     #6,d3
    and.l   #3,d3
     
    cmp.b   #0,d3
    beq     asr_lsl_byte            ;print .B
    cmp.b   #1,d3
    beq     asr_lsl_word            ;print .W
    cmp.b   #2,d3
    beq     asr_lsl_long            ;print .L
     
asr_lsl_byte                        ;print size of ASR/LSL
    jsr     print_byte
    bra     asr_lsl_reg_print_ea_cont   
  
asr_lsl_word
    jsr     print_word
    bra     asr_lsl_reg_print_ea_cont

asr_lsl_long
    jsr     print_long
    bra     asr_lsl_reg_print_ea_cont     
      
asr_lsl_reg_print_ea_cont           ;print EA of LSL, ASR register shifting
    move.w  d2,d3
    lsr     #5,d3
    and.l   #1,d3
    cmp.b   #0,d3
    beq     asr_lsl_count_print_ea  ;print EA for immediate
    bra     asr_lsl_data_print_ea   ;print EA for data register

asr_lsl_count_print_ea              ;print EA for immadiate
      move.w  d2,d4
      divu.w  #$100,d4
      lsr     #1,d4
      
      move.w  d2,d7
      move.b  #0,d6                 D6: destination mode
      and.w   d2,d7
      and.w   #7,d7                 D7: destination register number
            
      move.b  #6,d0                 ; move #6,d0
      move.b  #$23,d1               ; move hex character value '#' to d1
      trap    #15                   ; display a '#'
      
      move.b  d4,d1
      move.b  #3,d0                 ; print number
      trap    #15
      
      move.b  #6,d0
      move.b  #$2C,d1               ; display a comma
      Trap    #15
    
      JSR Destination *jump to SR Destination for printing
      JSR destinationRegister *jump to SR destinationRegister for printing
      
      **********************************CR,LF,Back up to main loop
      bra addr_setup             *goes back up to addr_setup to start next address
                                 *might have a branch here to do the enter tab functionality and 
                                 *increment counter
   

asr_lsl_data_print_ea               ;print EA for data register
      move.w  d2,d5
      divu.w  #$100,d5
      lsr     #1,d5
      move.w  #0,d4
      
      move.w  d2,d7
      move.b  #0,d6                 D6: destination mode
      and.w   #7,d7                 D7: destination register number
            
      jsr     eaPrint

 
print_byte
      lea     _B,a1                 ;print '.B'
      move.b  #14,d0
      trap    #15
      rts
     
print_word
      lea     _W,a1                 ;print '.W'
      move.b  #14,d0
      trap    #15
      rts
      
print_long
      lea     _L,a1                 ;print '.L'
      move.b  #14,d0
      trap    #15
      rts

************************************************************************************************************************    
*******************************************************START OF EA PART*************************************************    
********************************EA Print***********************
 
eaPrint
    JSR Source  ; jump to SR source for printing
    JSR sourceRegister  *jump to SR sourceRegister for printing
    JSR MoreNeededSource    *jump to SR MoreNeededSource for printing
    
    move.b  #6,d0
    move.b  #$2C,d1 ; display a comma
    Trap    #15
    
    JSR Destination *jump to SR Destination for printing
    JSR destinationRegister *jump to SR destinationRegister for printing
    JSR MoreNeededDestination *jump to SR MoreNeededDestination for printing
    
    
    bra addr_setup             *goes back up to addr_setup to start next address
                               *might have a branch here to do the enter tab functionality and 
                               *increment counter
Source 
    move.l  d4,d0   *move the source type from d4 to d0
    add.b   #1,d3   *add 1 to d3 to tell if a source or destination******************************************new
    bra     Type
Destination
    move.l d6,d0    *move the destination type from d4 to d0
    add.b   #1,d3   *add 1 to d3 to tell if a source or destination*********************************************new

Type *compares what was just put into d0 from either d4 or d6, and branches based on type
    cmp #0,d0
    beq dataRegister    
    cmp #1,d0
    beq addRegister
    cmp #2,d0
    beq indAddRegister 
    cmp #3,d0
    beq indAddRegister 
    cmp #4,d0
    beq indAddRegisterMinus 
    cmp #7,d0
    beq Address_Data
    
dataRegister
    move.b  #6,d0   ; display a 'D'
    move.b  #$44,d1
    trap    #15
    RTS

addRegister 
    move.b  #6,d0   ; move #6,d0
    move.b  #$41,d1 ; move hex character value 'A' to d1
    trap    #15     ; display a 'A'
    RTS  

indAddRegisterMinus
    move.b  #6,d0   ; move #6,d0
    move.b  #$2D,d1 ; move hex character value '-' to d1
    trap    #15     ; display a '-'
    
indAddRegister *does work on indirect add reg, and post incre and pre decr
    move.b  #6,d0   ; move #6,d0
    move.b  #$28,d1 ; move hex character value '(' to d1
    trap    #15     ; display a '('
    
    move.b  #6,d0   ; move #6,d0
    move.b  #$41,d1 ; move hex character value 'A' to d1
    trap    #15     ; display a 'A'
    RTS
   
Address_Data
    cmp.b #1,d3 ; if 1, then source. if not destination
    beq source_Address_Data
    bra destination_Address_Data

source_Address_Data 
    cmp.b   #4,d5   ; if source is data
    beq     Data    
    
    JSR     Address ; prints a '$' if an address
    move.b  d5,d2   ; moves either 0 or 1 to d2 so correct conversion of address is done
    JSR     add_for_address ;add the correct amount so conversion can be done
    
    cmp.b   #0,d5   ; if source is a word address
    beq     byte_word_read_in
    cmp.b   #1,d5   ; if source is a long address
    beq     long_read_in

destination_Address_Data
    JSR     Address
    move.b  d7,d2   ; moves either 0 or 1 to d2 so correct conversion of address is done
    JSR     add_for_address ;add the correct amount so conversion can be done
    
    cmp.b   #0,d7
    beq     destination_byte_word_read_in
    cmp.b   #1,d7
    beq     destination_long_read_in

add_for_address *this will setup our program for correct read in
    cmp.b   #$00,d2
    beq     word_three
    add.b   #$01,d2
    RTS
word_three  *adds 3 so our program recognizes a word
    add.b   #$03,d2
    RTS
    
Address
    move.b  #6,d0   ; move #6,d0
    move.b  #$24,d1 ; move hex character value '$' to d1
    trap    #15     ; display a '$'
    RTS

Data  
    move.b  #6,d0   ; move #6,d0
    move.b  #$23,d1 ; move hex character value '#' to d1
    trap    #15     ; display a '#'
    
    move.b  #6,d0   ; move #6,d0
    move.b  #$24,d1 ; move hex character value '$' to d1
    trap    #15     ; display a '$'
    
    cmp.b   #2,d2   ; uses first line of ea print to determine whether read in is long, word or byte******first line of ea print
    beq     long_read_in
    bra     byte_word_read_in
    
long_read_in    *this reads in a long in source
    move.l  (A2)+,d5
    RTS
byte_word_read_in   *this reads in a byte/word in source
    move.w  (A2)+,d5
    RTS   
destination_long_read_in    *this reads in a long in dest
    move.l  (A2)+,d7
    RTS
destination_byte_word_read_in   *this reads in a byte/word in dest
    move.w  (A2)+,d7
    RTS
 
sourceRegister
    cmp.b   #7,d4 
    beq    Convert_to_char *branch to conversion code
    
    move.b  #6,d0 *if not a 7 in d4, print source reg
    move.b  d5,d1
    add.b   #$30,d1
    trap    #15
    RTS
    
destinationRegister
    cmp.b   #7,d6
    beq    non_dest_Reg_print *branch to conversion setup
    
    move.b  #6,d0
    move.b  d7,d1
    add.b   #$30,d1
    trap    #15
    RTS
    
non_dest_Reg_print *need to decode to hex before print. JSR  will now move.l d7,d5 so the coversion code will work for both source and destination. Then down to conversion code
    move.l  d7,d5
    bra     Convert_to_char
    
MoreNeededSource *cmps whats is in d4 to see if a ) or a )+ is needed
    cmp.b   #2,d4 *cmp to see if a right parenteses is needed before comma
    beq rightParentheses
    cmp.b   #3,d4
    beq rightParenthesesPlus   
    cmp.b   #4,d4
    beq rightParentheses
    RTS *Nothing more needed
    
MoreNeededDestination   *cmps whats is in d6 to see if a ) or a )+ is needed
    cmp.b   #2,d6 *cmp to see if a right parenteses is needed before comma
    beq rightParentheses
    cmp.b   #3,d6
    beq rightParenthesesPlus   
    cmp.b   #4,d6
    beq rightParentheses
    RTS *Nothing more needed
    
rightParentheses
    move.b  #6,d0   ; move #6,d0
    move.b  #$29,d1 ; move hex character value ')' to d1
    trap    #15     ; display a ')'
    RTS
rightParenthesesPlus   
    move.b  #6,d0   ; move #6,d0
    move.b  #$29,d1 ; move hex character value ')' to d1
    trap    #15     ; display a ')'
    
    move.b  #$2B,d1 ; move hex character value '+' to d1
    trap    #15     ; display a '+'
    RTS
    
Convert_to_char *this checks if what needs to be converted is a byte, word or long
    cmp.b   #1,d2   
    beq     ten         *byte to be converted
    cmp.b   #2,d2
    beq     long        *long to be decoded
    cmp.b   #3,d2
    beq     thousand    *word to be decoded
    
long
    move.w  d5,d4       *moves last 4 bytes of long to be decoded later
    move.w  #$0000,d5   *zeros out last 4 bytes of long 
    swap    d5          *swaps so first 4 bytes are converted
    JSR thousand        *starts converting
    move.w  d4,d5       *moves last 4 bytes to d5 to be converted
    JSR thousand        *strats converting last 4 bytes
    RTS                 *returns to eaPrint
thousand
    divu    #$1000,d5   *divides by 1000 to get first digit in word
    JSR     next
    JSR     Print
    BRA     hundred
    
hundred
    divu    #$100,d5    *divides by 100 to get first digit in the next 3 nibbles
    JSR     next
    JSR     Print
    BRA     ten

ten
    divu    #$10,d5     *divides by 10 to get first digit in the byte
    JSR     next
    JSR     Print
    BRA     one
one
    JSR     next
    JSR     Print
    BRA     Complete
next            
    cmp.b   #9,d5       *checks to see if byte is a letter or number
    bgt     letter
    
    add.b   #$30,d5     *adds 30 for numbers
    bra     next2 

letter  
    add.b   #$37,d5     *adds 37 for letters
next2
    RTS
    
Print 
    move.b  d5,d1   *moves the byte that was just coverted to a char to d1 dor printing
    
    move.b  #6,d0
    trap    #15
    move.w  #0000,d5    *clrs the byte that was just printed
    swap    d5          *swaps d5 so the rest of the data/address can be converted to a char
    RTS
Complete    ********************************************wasnt in the code, put back
    RTS
    
    
************************************************
*Invalid opcode
************************************************  
invalid_opcode
    lea     invalid_data_print,a1
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    
    swap    d1  *setup code for printing
    move.w  d1,d5
    swap    d1
    clr.w   d1
    
    jsr     address *print a $
    move.b #3,d2
    jsr Convert_to_char *convert code
    
    lea     Return,a1   *CR, LF 
    MOVE.B  #14,D0       ; MOVES THE NUMBER 14 INTO DATA REGISTER D0
    TRAP    #15          ; DISPLAYS THE MESSAGE
    
    bra     addr_setup  *back up to main loop
    
    
************************************************
*swap_registers
************************************************
swap_registers    
    move.b  d6,d2
    clr     d6  *makes d6 0 for correct printing in eaPrint
    cmp.b   #3,d2   *checks to see if registers need to be flipped
    bgt    swap_dn
    RTS
    
swap_dn     *this swaps registers d4&d6, d5&d7 for correct printing in eaPrint
    
    move.b  d4,d3
    move.b  d6,d4
    move.b  d3,d6
    
    move.b  d5,d3
    move.b  d7,d5
    move.b  d3,d7
    RTS

*******************************************************END OF EA*****************************************************        


    SIMHALT             ; halt simulator
    
*******************************************************VARIABLES*****************************************************  
*variables for IO
Welcome    dc.b    'Welcome to the Cloud Guys Disassembler Program ',CR,LF,0
AddrSpec    dc.b    'Addresses entered must be even 8 digit numbers within the specified range, ',CR,LF
            dc.b    'with UPPERCASE LETTERS please.',0
Counter dc.b    00
Return dc.b  '',CR,0
AddrStart   ds.b    10
AddrEnd     ds.b    10
AddrCurrent ds.b    10
AskContinueDecode   dc.b    'Would you like to continue decoding? Y for yes, N for no in UPPERCASE: ',0
AddrErrOut dc.b    'Incorrect address type',0         
StartAddress_out dc.b    'Please enter a starting address within the boundaries 00003000 and 0000FFFF: ',0
EndAddress_out dc.l      'Please enter an ending address between ',0
End_out dc.b             ' and 0000FFFF: ',0
space   equ     $20
invalid_data_print  dc.b    'DATA ',0 
any_key_to_continue dc.b    'Press any key to display the next screen.',0
CR  EQU     $0D ; carrige return
LF  EQU     $0A ; line feed 


*variables for move
move_b  dc.b    'move.b ',0
move_l  dc.b    'move.l ',0
move_w  dc.b    'move.w ',0

*variables for branches
BranchAddress   DS.B    10
bra_out dc.b 'bra',0
bcc_out dc.b 'bcc',0
beq_out dc.b 'beq',0
blt_out dc.b 'blt',0
bge_out dc.b 'bge',0

*variables for sub
sub_b   dc.b    'sub.b ',0
sub_w   dc.b    'sub.w ',0
sub_l   dc.b    'sub.l ',0

*variables for muls
muls_w  dc.b    'muls.w ',0

*variables for and
and_b   dc.b    'and.b ',0
and_w   dc.b    'and.w ',0
and_l   dc.b    'and.l ',0
*variables for movem

*variables for lea

*variables for add
_ADD dc.b   'ADD',0

*variables for asr,lsl
_B  dc.b    '.B ',0
_W  dc.b    '.W ',0
_L  dc.b    '.L ',0
asr dc.b    'ASR',0
lsl dc.b    'LSL',0

*variables for nop,rts,movem,lea,
NOP_OPCODE      DC.B    'NOP',0
RTS_OPCODE      DC.B    'RTS',0
LEA_OPCODE      DC.B    'LEA',0
JSR_OPCODE      DC.B    'JSR',0
MULS_OPCODE     DC.B    'MULS.L'
MOVEM_OPCODE    DC.B    'MOVEM',0
DOLLARSIGN      DC.B    '$',0
WORD_size       DC.B    '.W',0
LONG_size       DC.B    '.L',0
DISPLAY_SPACE   DC.B    '  ',0
DISShor_SPACE   DC.B    '      ',0
DISPLAY_comma   DC.B    ',',0
DISP_DOLLAR_SIGN DC.B   '$',0
NOTL_OPCODE     DC.B    'NOT.L',0
NOTW_OPCODE     DC.B    'NOT.W',0
NOTB_OPCODE     DC.B    'NOT.B',0
EA_TYPE         DS.B     10

*other variables

    END    START        ; last line of source






*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
