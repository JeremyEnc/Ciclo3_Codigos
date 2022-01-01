library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ModeloMoore is
    Port ( tiempo, sensor, clk, sp : in  STD_LOGIC;
           PA, PC, PARO, AVANCE : out  STD_LOGIC;
           Q1, Q0 : inout  STD_LOGIC);
end ModeloMoore;

architecture Behavioral of ModeloMoore is

	TYPE estados is(A,B,C,D);
	signal estAct, estFut: estados; 

begin

	process(estAct, tiempo, sensor, sp)
	begin
		case estAct is
			
			when A => 
				PA <= '1'; PC <= '0'; PARO <= '0'; 
				AVANCE <= '0'; Q1 <= '0'; Q0 <= '0';
				
				if(tiempo = '1') then
					estFut <= B;
				else
					estFut <= A;
				end if;
			when B =>
				PA <= '0'; PC <= '1'; PARO <= '0'; 
				AVANCE <= '0'; Q1 <= '0'; Q0 <= '1';
				
				if(sp = '1') then
					estFut <= C;
				else
					estFut <= B;
				end if;
			when C => 
				PA <= '0'; PC <= '0'; PARO <= '0'; 
				AVANCE <= '1'; Q1 <= '1'; Q0 <= '0';
				
				if(sensor = '1') then
					estFut <= D;
				else
					estFut <= C;
				end if;
			when D =>
				PA <= '0'; PC <= '0'; PARO <= '1'; 
				AVANCE <= '0'; Q1 <= '1'; Q0 <= '1';
				
				estFut <= A;
			end case;
		end process;
		
		process(clk)
		begin
			if(clk'event and clk = '1') then
				estAct <= estFut;
			end if;
		end process;
				
end Behavioral;

