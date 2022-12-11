//
//  KanjiResourceScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared

struct KanjiResourceScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        
        ZStack {
            VStack {
                
                VStack(alignment: .leading) {
                    Text("Grades")
                        .font(.title)
                        .fontWeight(.semibold)
                    
                    let columns = [
                        GridItem(.adaptive(minimum: 72), spacing: 24)
                    ]
                    
                    Text("小学校（1-6）")
                        .font(.title2)
                        .fontWeight(.semibold)
                    
                    LazyVGrid(columns: columns, spacing: 24) {
                        ForEach(1...6, id: \.self) { grade in
                            NavigationLink {
                                KanjiListScreen(query: KanjiQueryGrade(grade: Int32(grade)))
                            } label: {
                                KanjiGradeItem(grade: "\(grade)")
                            }
                        }
                    }
                    .padding(.vertical)
                    
                    Text("中学校（7-9）")
                        .font(.title2)
                        .fontWeight(.semibold)
                    
                    LazyVGrid(columns: columns) {
                        ForEach(7...9, id: \.self) { grade in
                            NavigationLink {
                                KanjiListScreen(query: KanjiQueryGrade(grade: Int32(grade)))
                            } label: {
                                KanjiGradeItem(grade: "\(grade)")
                            }
                        }
                    }
                    .padding(.vertical)
                    
                    NavigationLink {
                        KanjiListScreen(query: KanjiQueryAll())
                    } label: {
                        AllSchoolItem()
                    }
                }
                .padding()
                
                Spacer()
            }
        }
    }
}
