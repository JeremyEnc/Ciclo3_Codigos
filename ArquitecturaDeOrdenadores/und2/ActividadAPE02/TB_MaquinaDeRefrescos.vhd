--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   20:12:14 12/31/2021
-- Design Name:   
-- Module Name:   C:/Users/jere_/Documents/circuitosISE/ActividadAPE02/TB_MaquinaDeRefrescos.vhd
-- Project Name:  ActividadAPE02
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: MaquinaDeRefrescos
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
 
ENTITY TB_MaquinaDeRefrescos IS
END TB_MaquinaDeRefrescos;
 
ARCHITECTURE behavior OF TB_MaquinaDeRefrescos IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT MaquinaDeRefrescos
    PORT(
         clk : IN  std_logic;
         moneda : IN  std_logic;
         mp : IN  std_logic;
         precio : IN  std_logic;
         lc : IN  std_logic;
         ls : IN  std_logic;
         cap : OUT  std_logic;
         limpia : OUT  std_logic;
         sirve : OUT  std_logic;
         cambio : OUT  std_logic;
         dec : OUT  std_logic;
         im : OUT  std_logic;
         q2 : OUT  std_logic;
         q1 : OUT  std_logic;
         q0 : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal moneda : std_logic := '0';
   signal mp : std_logic := '0';
   signal precio : std_logic := '0';
   signal lc : std_logic := '0';
   signal ls : std_logic := '0';

 	--Outputs
   signal cap : std_logic;
   signal limpia : std_logic;
   signal sirve : std_logic;
   signal cambio : std_logic;
   signal dec : std_logic;
   signal im : std_logic;
   signal q2 : std_logic;
   signal q1 : std_logic;
   signal q0 : std_logic;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: MaquinaDeRefrescos PORT MAP (
          clk => clk,
          moneda => moneda,
          mp => mp,
          precio => precio,
          lc => lc,
          ls => ls,
          cap => cap,
          limpia => limpia,
          sirve => sirve,
          cambio => cambio,
          dec => dec,
          im => im,
          q2 => q2,
          q1 => q1,
          q0 => q0
        );


 

   -- Stimulus process
   stim_proc: process
   begin		
      
		wait for 10 us;
		moneda <= '1';
      mp <= '0';
      precio <= '0';
      lc <= '0';
      ls <= '0';
		wait for 10 us;
		moneda <= '0';
      mp <= '0';
      precio <= '0';
      lc <= '0';
      ls <= '0';
		wait for 10 us;
		moneda <= '0';
      mp <= '0';
      precio <= '0';
      lc <= '0';
      ls <= '0';
		wait for 10 us;
		moneda <= '0';
      mp <= '0';
      precio <= '0';
      lc <= '0';
      ls <= '0';
		wait for 10 us;
		moneda <= '0';
      mp <= '0';
      precio <= '1';
      lc <= '1';
      ls <= '0';
		wait for 10 us;
		moneda <= '0';
      mp <= '0';
      precio <= '0';
      lc <= '0';
      ls <= '1';
      wait;
   end process;

END;
