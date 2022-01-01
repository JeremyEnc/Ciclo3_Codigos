--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:03:43 12/31/2021
-- Design Name:   
-- Module Name:   C:/Users/jere_/Documents/circuitosISE/ActividadAPE02/TB_Alarma.vhd
-- Project Name:  ActividadAPE02
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: Alarma
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
 
ENTITY TB_Alarma IS
END TB_Alarma;
 
ARCHITECTURE behavior OF TB_Alarma IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT Alarma
    PORT(
         clk : IN  std_logic;
         fx : IN  std_logic;
         cont4 : IN  std_logic;
         boton : IN  std_logic;
         reset : OUT  std_logic;
         inc : OUT  std_logic;
         activa : OUT  std_logic;
         alarm : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal fx : std_logic := '0';
   signal cont4 : std_logic := '0';
   signal boton : std_logic := '0';

 	--Outputs
   signal reset : std_logic;
   signal inc : std_logic;
   signal activa : std_logic;
   signal alarm : std_logic;

BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: Alarma PORT MAP (
          clk => clk,
          fx => fx,
          cont4 => cont4,
          boton => boton,
          reset => reset,
          inc => inc,
          activa => activa,
          alarm => alarm
        );

   -- Stimulus process
   stim_proc: process
   begin		
		wait for 10 us;
		fx <= '0';
      cont4 <='0';
      boton <= '0';
		wait for 10 us;
		fx <= '0';
      cont4 <='0';
      boton <= '1';
		wait for 10 us;
		fx <= '0';
      cont4 <='1';
      boton <= '0';
		wait for 10 us;
		fx <= '1';
      cont4 <='0';
      boton <= '1';
		wait for 10 us;
		fx <= '0';
      cont4 <='1';
      boton <= '0';
		
      wait;
   end process;

END;
