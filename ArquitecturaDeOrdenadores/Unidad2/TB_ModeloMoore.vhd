--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   19:34:06 12/31/2021
-- Design Name:   
-- Module Name:   C:/Users/jere_/Documents/circuitosISE/ActividadAPE02/TB_ModeloMoore.vhd
-- Project Name:  ActividadAPE02
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: ModeloMoore
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY TB_ModeloMoore IS
END TB_ModeloMoore;
 
ARCHITECTURE behavior OF TB_ModeloMoore IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT ModeloMoore
    PORT(
         tiempo : IN  std_logic;
         sensor : IN  std_logic;
         clk : IN  std_logic;
         sp : IN  std_logic;
         PA : OUT  std_logic;
         PC : OUT  std_logic;
         PARO : OUT  std_logic;
         AVANCE : OUT  std_logic;
         Q1 : INOUT  std_logic;
         Q0 : INOUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal tiempo : std_logic := '0';
   signal sensor : std_logic := '0';
   signal clk : std_logic := '0';
   signal sp : std_logic := '0';

	--BiDirs
   signal Q1 : std_logic;
   signal Q0 : std_logic;

 	--Outputs
   signal PA : std_logic;
   signal PC : std_logic;
   signal PARO : std_logic;
   signal AVANCE : std_logic;

   
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: ModeloMoore PORT MAP (
          tiempo => tiempo,
          sensor => sensor,
          clk => clk,
          sp => sp,
          PA => PA,
          PC => PC,
          PARO => PARO,
          AVANCE => AVANCE,
          Q1 => Q1,
          Q0 => Q0
        );


   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
		wait for 10 us;
      tiempo <= '0';
		sensor <= '0';
		sp <= '0';
		wait for 10 us;
      tiempo <= '1';
		sensor <= '0';
		sp <= '0';
		wait for 10 us;
      tiempo <= '0';
		sensor <= '0';
		sp <= '1';
		wait for 10 us;
      tiempo <= '0';
		sensor <= '1';
		sp <= '0';
		

		
      -- insert stimulus here 

      wait;
   end process;

END;
